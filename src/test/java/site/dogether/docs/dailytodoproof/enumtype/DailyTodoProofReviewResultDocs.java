package site.dogether.docs.dailytodoproof.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.docs.util.RestDocsEnumType;

@Getter
@RequiredArgsConstructor
public enum DailyTodoProofReviewResultDocs implements RestDocsEnumType {

    APPROVE("인정", "APPROVE"),
    REJECT("노인정", "REJECT")
    ;

    private final String description;
    private final String requestValue;
}
