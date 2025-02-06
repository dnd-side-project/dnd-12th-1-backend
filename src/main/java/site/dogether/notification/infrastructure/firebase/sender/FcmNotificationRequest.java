package site.dogether.notification.infrastructure.firebase.sender;

import com.google.firebase.messaging.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.notification.sender.NotificationRequest;

@Getter
@RequiredArgsConstructor
public abstract class FcmNotificationRequest implements NotificationRequest {

    public final String fcmToken;

    abstract Message convertFcmMessage();
}
