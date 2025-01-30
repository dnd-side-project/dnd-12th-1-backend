package site.dogether.docs.challengegroup.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.challengegroup.domain.ChallengeGroupDurationOption;
import site.dogether.docs.util.RestDocsEnumType;

@Getter
@RequiredArgsConstructor
public enum ChallengeGroupDurationOptionDocs implements RestDocsEnumType {

    THREE_DAYS("3일", "3"),
    SEVEN_DAYS("7일", "7"),
    FOURTEEN_DAYS("14일", "14"),
    TWENTY_EIGHT_DAYS("28일", "28")
    ;

    private static final int enumValueCount = ChallengeGroupDurationOption.values().length;

    private final String description;
    private final String requestValue;

    public static RestDocsEnumType[] getValues() {
        final ChallengeGroupDurationOptionDocs[] values = ChallengeGroupDurationOptionDocs.values();
        RestDocsEnumType.checkDocsValueCountIsEqualToEnumValueCount(enumValueCount, values.length);
        return values;
    }
}
