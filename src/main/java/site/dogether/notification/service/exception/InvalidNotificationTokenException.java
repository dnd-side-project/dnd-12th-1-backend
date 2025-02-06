package site.dogether.notification.service.exception;

public class InvalidNotificationTokenException extends RuntimeException {

    public InvalidNotificationTokenException(final String message) {
        super(message);
    }
}
