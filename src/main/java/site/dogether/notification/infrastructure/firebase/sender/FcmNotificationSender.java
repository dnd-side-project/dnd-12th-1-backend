package site.dogether.notification.infrastructure.firebase.sender;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.dogether.notification.sender.NotificationRequest;
import site.dogether.notification.sender.NotificationSender;
import site.dogether.notification.service.exception.InvalidNotificationTokenException;

@Slf4j
@Component
public class FcmNotificationSender implements NotificationSender {

    @Override
    public void send(final NotificationRequest request) {
        final FcmNotificationRequest fcmNotificationRequest = (FcmNotificationRequest) request;
        sendPushNotification(fcmNotificationRequest.convertFcmMessage());
    }

    private void sendPushNotification(final Message fcmMessage) {
        try {
            final String response = FirebaseMessaging.getInstance().send(fcmMessage);
            log.info("푸시 알림 전송 완료 - {}", response);
        } catch (final FirebaseMessagingException e) {
            log.error("푸시 알림 전송에 실패하였습니다.", e);
            handleFcmException(e.getMessage());
        }
    }

    private void handleFcmException(final String errorResponse) {
        if (checkInvalidFcmTokenResponse(errorResponse)) {
            throw new InvalidNotificationTokenException("유효하지 않은 FCM 토큰입니다.");
        }
    }

    private boolean checkInvalidFcmTokenResponse(final String errorResponse) {
        return errorResponse.contains("The registration token is not a valid FCM registration token");
    }
}
