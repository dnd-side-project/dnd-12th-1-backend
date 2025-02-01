package site.dogether.docs.dailytodocertification.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.dailytodocertification.domain.DailyTodoCertificationReviewResult;
import site.dogether.docs.util.RestDocsEnumType;

@Getter
@RequiredArgsConstructor
public enum DailyTodoCertificationReviewResultDocs implements RestDocsEnumType {

    APPROVE("인정", "APPROVE"),
    REJECT("노인정", "REJECT")
    ;

    private static final int enumValueCount = DailyTodoCertificationReviewResult.values().length;

    private final String description;
    private final String requestValue;

    public static RestDocsEnumType[] getValues() {
        final DailyTodoCertificationReviewResultDocs[] values = DailyTodoCertificationReviewResultDocs.values();
        RestDocsEnumType.checkDocsValueCountIsEqualToEnumValueCount(enumValueCount, values.length);
        return values;
    }
}
