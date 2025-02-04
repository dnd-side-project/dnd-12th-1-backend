package site.dogether.notification.infrastructure.firebase.sender;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.notification.sender.NotificationRequest;

import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class FcmNotificationRequest implements NotificationRequest {

    public final Long recipientId;

    abstract List<FcmMessageWithToken> convertFcmMessages(final List<String> tokens);
}
