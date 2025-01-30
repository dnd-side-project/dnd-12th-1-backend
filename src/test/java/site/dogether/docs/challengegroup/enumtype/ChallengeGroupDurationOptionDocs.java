package site.dogether.docs.challengegroup.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.docs.util.RestDocsEnumType;

@Getter
@RequiredArgsConstructor
public enum ChallengeGroupDurationOptionDocs implements RestDocsEnumType {

    THREE_DAYS("3일", "3"),
    SEVEN_DAYS("7일", "7"),
    FOURTEEN_DAYS("14일", "14"),
    TWENTY_EIGHT_DAYS("28일", "28")
    ;

    private final String description;
    private final String requestValue;
}
