package site.dogether.notification.infrastructure.firebase.sender;

import com.google.firebase.messaging.Message;

public record FcmMessageWithToken(Message message, String token) {
}
