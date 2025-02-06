package site.dogether.developer_test_api.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.dogether.developer_test_api.DeveloperTestApiResponse;
import site.dogether.notification.infrastructure.firebase.sender.SimpleFcmNotificationRequest;
import site.dogether.notification.sender.NotificationSender;

/**
 * 클라이언트 개발자 테스트용 API
 */
@Profile("!prod")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class DeveloperTestNotificationController {

    private final NotificationSender notificationSender;

    @PostMapping("/send-notification")
    public DeveloperTestApiResponse sendNotification(
        @RequestBody final SendNotificationRequest request
    ) {
        final SimpleFcmNotificationRequest notificationRequest = new SimpleFcmNotificationRequest(
            request.token(),
            request.title(),
            request.body());
        notificationSender.send(notificationRequest);
        return new DeveloperTestApiResponse("푸시 알림 테스트 API 실행 완료.");
    }
}
