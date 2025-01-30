package site.dogether.docs.dailytodoproof.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.dailytodoproof.domain.DailyTodoProofReviewResult;
import site.dogether.docs.util.RestDocsEnumType;

@Getter
@RequiredArgsConstructor
public enum DailyTodoProofReviewResultDocs implements RestDocsEnumType {

    APPROVE("인정", "APPROVE"),
    REJECT("노인정", "REJECT")
    ;

    private static final int enumValueCount = DailyTodoProofReviewResult.values().length;

    private final String description;
    private final String requestValue;

    public static RestDocsEnumType[] getValues() {
        final DailyTodoProofReviewResultDocs[] values = DailyTodoProofReviewResultDocs.values();
        RestDocsEnumType.checkDocsValueCountIsEqualToEnumValueCount(enumValueCount, values.length);
        return values;
    }
}
