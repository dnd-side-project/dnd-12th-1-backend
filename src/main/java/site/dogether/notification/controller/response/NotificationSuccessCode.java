package site.dogether.notification.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.common.controller.response.SuccessCode;

@Getter
@RequiredArgsConstructor
public enum NotificationSuccessCode implements SuccessCode {

    SAVE_NOTIFICATION_TOKEN("NTS-0001", "푸시 알림 토큰을 저장하였습니다."),
    DELETE_NOTIFICATION_TOKEN("NTS-0002", "푸시 알림 토큰을 삭제하였습니다.")
    ;

    private final String value;
    private final String message;
}
