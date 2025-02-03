package site.dogether.notification.infrastructure.firebase.sender;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import site.dogether.member.infrastructure.entity.MemberJpaEntity;
import site.dogether.notification.infrastructure.entity.NotificationTokenJpaEntity;
import site.dogether.notification.infrastructure.repository.NotificationTokenJpaRepository;
import site.dogether.notification.sender.NotificationRequest;
import site.dogether.notification.sender.NotificationSender;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class FcmNotificationSender implements NotificationSender {

    private final NotificationTokenJpaRepository notificationTokenJpaRepository;

    @Override
    public void send(final NotificationRequest request) {
        final FcmNotificationRequest fcmNotificationRequest = (FcmNotificationRequest) request;
        final List<String> fcmTokens = findFcmTokens(fcmNotificationRequest.getRecipient());
        fcmNotificationRequest.convertFcmMessages(fcmTokens).forEach(this::sendPushNotification);
    }

    private List<String> findFcmTokens(final MemberJpaEntity recipient) {
        return notificationTokenJpaRepository.findAllByMember(recipient)
            .stream()
            .map(NotificationTokenJpaEntity::getTokenValue)
            .toList();
    }

    private void sendPushNotification(final FcmMessageWithToken fcmMessageWithToken) {
        try {
            final String response = FirebaseMessaging.getInstance().send(fcmMessageWithToken.message());
            log.info("푸시 알림 전송 완료 - {}", response);
        } catch (final FirebaseMessagingException e) {
            log.error("푸시 알림 전송에 실패하였습니다.", e);
            handleInvalidFcmToken(e.getMessage(), fcmMessageWithToken.token());
        }
    }

    private void handleInvalidFcmToken(final String errorResponse, final String fcmToken) {
        if (checkInvalidFcmTokenResponse(errorResponse)) {
            notificationTokenJpaRepository.deleteAllByValue(fcmToken);
            log.info("유효하지 않은 FCM 토큰 제거 - {}", fcmToken);
        }
    }

    private boolean checkInvalidFcmTokenResponse(final String errorResponse) {
        return errorResponse.contains("The registration token is not a valid FCM registration token");
    }

    /**
     * 클라이언트 개발용
     */
    public void testSendNotification(final String token, final String title, final String body) {
        final Notification notification = Notification.builder()
                                              .setTitle(title)
                                              .setBody(body)
                                              .build();
        final Message message = Message.builder()
                                    .setNotification(notification)
                                    .setToken(token)
                                    .build();

        sendPushNotification(new FcmMessageWithToken(message, token));
    }
}
