package site.dogether.dailytodoproof.domain;

import lombok.RequiredArgsConstructor;
import site.dogether.common.constant.EnumType;

@RequiredArgsConstructor
public enum DailyTodoProofReviewResult implements EnumType {
    APPROVE("인정"),
    REJECT("노인정")
    ;

    private final String description;

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getValue() {
        return this.name();
    }
}
