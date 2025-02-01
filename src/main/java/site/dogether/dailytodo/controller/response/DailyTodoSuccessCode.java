package site.dogether.dailytodo.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.common.controller.response.SuccessCode;

@RequiredArgsConstructor
@Getter
public enum DailyTodoSuccessCode implements SuccessCode {

    CREATE_DAILY_TODOS("DTS-0001", "데일리 투두가 작성되었습니다."),
    CERTIFY_DAILY_TODO("DTS-0002", "데일리 투두 수행 인증이 완료되었습니다."),
    GET_YESTERDAY_DAILY_TODOS("DTS-0003", "어제 작성된 데일리 투두 내용들이 조회되었습니다."),
    ;

    private final String value;
    private final String message;
}
