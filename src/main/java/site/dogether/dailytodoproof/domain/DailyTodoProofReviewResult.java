package site.dogether.dailytodoproof.domain;

import lombok.RequiredArgsConstructor;
import site.dogether.common.docs.RestDocsEnumType;

@RequiredArgsConstructor
public enum DailyTodoProofReviewResult implements RestDocsEnumType {
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
