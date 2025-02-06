package site.dogether.notification.infrastructure.firebase.sender;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class SimpleFcmNotificationRequest extends FcmNotificationRequest {

    private final String title;
    private final String body;

    public SimpleFcmNotificationRequest(
        final String fcmToken,
        final String title,
        final String body
    ) {
        super(fcmToken);
        this.title = title;
        this.body = body;
    }

    @Override
    public Message convertFcmMessage() {
        return Message.builder()
                   .setNotification(createNotification())
                   .setToken(fcmToken)
                   .build();
    }

    private Notification createNotification() {
        return Notification.builder()
                   .setTitle(title)
                   .setBody(body)
                   .build();
    }
}
