package site.dogether.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.dogether.common.controller.response.ApiResponse;
import site.dogether.notification.controller.request.DeleteNotificationTokenRequest;
import site.dogether.notification.controller.request.SaveNotificationTokenRequest;

import static site.dogether.notification.controller.response.NotificationSuccessCode.DELETE_NOTIFICATION_TOKEN;
import static site.dogether.notification.controller.response.NotificationSuccessCode.SAVE_NOTIFICATION_TOKEN;

@RequestMapping("/api/notification")
@RestController
public class NotificationController {

    @PostMapping("/tokens")
    public ResponseEntity<ApiResponse<Void>> saveNotificationToken(@RequestBody final SaveNotificationTokenRequest request) {
        return ResponseEntity.ok(ApiResponse.success(SAVE_NOTIFICATION_TOKEN));
    }

    @DeleteMapping("/tokens")
    public ResponseEntity<ApiResponse<Void>> deleteNotificationToken(@RequestBody final DeleteNotificationTokenRequest request) {
        return ResponseEntity.ok(ApiResponse.success(DELETE_NOTIFICATION_TOKEN));
    }
}
