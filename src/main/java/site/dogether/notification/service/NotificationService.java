package site.dogether.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.dogether.notification.infrastructure.entity.NotificationTokenJpaEntity;
import site.dogether.notification.infrastructure.firebase.sender.SimpleFcmNotificationRequest;
import site.dogether.notification.infrastructure.repository.NotificationTokenJpaRepository;
import site.dogether.notification.sender.NotificationSender;
import site.dogether.notification.service.exception.InvalidNotificationTokenException;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final NotificationTokenJpaRepository notificationTokenJpaRepository;
    private final NotificationSender notificationSender;

    @Transactional
    public void sendNotification(
        final Long recipientId,
        final String title,
        final String body
    ) {
        notificationTokenJpaRepository.findAllByMember_Id(recipientId).forEach(
            notificationToken -> sndNotification(notificationToken, title, body));
    }

    private void sndNotification(final NotificationTokenJpaEntity notificationToken, String title, String body) {
        try {
            final SimpleFcmNotificationRequest simpleFcmNotificationRequest = new SimpleFcmNotificationRequest(notificationToken.getValue(), title, body);
            notificationSender.send(simpleFcmNotificationRequest);
        } catch (final InvalidNotificationTokenException e) {
            notificationTokenJpaRepository.deleteAllByValue(notificationToken.getValue());
            log.info("유효하지 않은 토큰 제거 - {}", notificationToken.getValue());
        }
    }
}
