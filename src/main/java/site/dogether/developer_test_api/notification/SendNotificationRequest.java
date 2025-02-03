package site.dogether.developer_test_api.notification;

public record SendNotificationRequest(
    String token,
    String title,
    String body
) {
}
