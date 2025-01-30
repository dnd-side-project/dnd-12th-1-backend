package site.dogether.dailytodoproof.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.common.controller.response.SuccessCode;

@RequiredArgsConstructor
@Getter
public enum DailyTodoProofSuccessCode implements SuccessCode {

    REVIEW_DAILY_TODO_PROOF("TPS-0001", "데일리 투두 수행 인증 검사가 완료되었습니다."),
    GET_DAILY_TODO_PROOFS_FOR_REVIEW("TPS-0002", "검사할 투두 인증글을 모두 조회하였습니다."),
    GET_DAILY_TODO_PROOF_FOR_REVIEW_BY_ID("TPS-0003", "검사할 투두 인증글을 조회하였습니다."),
    ;

    private final String value;
    private final String message;
}
