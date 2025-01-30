package site.dogether.docs.challengegroup.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.challengegroup.domain.ChallengeGroupStartAtOption;
import site.dogether.docs.util.RestDocsEnumType;

@Getter
@RequiredArgsConstructor
public enum ChallengeGroupStartAtOptionDocs implements RestDocsEnumType {

    TODAY("오늘부터", "TODAY"),
    TOMORROW("내일부터", "TOMORROW")
    ;

    private static final int enumValueCount = ChallengeGroupStartAtOption.values().length;

    private final String description;
    private final String requestValue;

    public static RestDocsEnumType[] getValues() {
        final ChallengeGroupStartAtOptionDocs[] values = ChallengeGroupStartAtOptionDocs.values();
        RestDocsEnumType.checkDocsValueCountIsEqualToEnumValueCount(enumValueCount, values.length);
        return values;
    }
}
