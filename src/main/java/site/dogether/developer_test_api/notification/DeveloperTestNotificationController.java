package site.dogether.developer_test_api.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.dogether.developer_test_api.DeveloperTestApiResponse;
import site.dogether.notification.infrastructure.firebase.sender.FcmNotificationSender;

/**
 * 클라이언트 개발자 테스트용 API
 */
@Profile("!prod")
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class DeveloperTestNotificationController {

    private final FcmNotificationSender notificationSender;

    @PostMapping("/send-notification")
    public DeveloperTestApiResponse sendNotification(
        @RequestBody final SendNotificationRequest request
    ) {
        notificationSender.testSendNotification(request.token(), request.title(), request.body());
        return new DeveloperTestApiResponse("푸시 알림 테스트 API 실행 완료.");
    }
}
