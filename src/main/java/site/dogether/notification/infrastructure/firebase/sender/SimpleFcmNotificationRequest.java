package site.dogether.notification.infrastructure.firebase.sender;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import site.dogether.member.infrastructure.entity.MemberJpaEntity;

import java.util.List;

public class SimpleFcmNotificationRequest extends FcmNotificationRequest {

    private final String title;
    private final String body;

    public SimpleFcmNotificationRequest(
        final MemberJpaEntity recipient,
        final String title,
        final String body
    ) {
        super(recipient);
        this.title = title;
        this.body = body;
    }

    @Override
    public List<FcmMessageWithToken> convertFcmMessages(final List<String> tokens) {
        return tokens.stream()
                   .map(this::convertFcmMessage)
                   .toList();
    }

    private FcmMessageWithToken convertFcmMessage(final String token) {
        final Message message = Message.builder()
                                  .setNotification(createNotification())
                                  .setToken(token)
                                  .build();
        return new FcmMessageWithToken(message, token);
    }

    private Notification createNotification() {
        return Notification.builder()
                   .setTitle(title)
                   .setBody(body)
                   .build();
    }
}
