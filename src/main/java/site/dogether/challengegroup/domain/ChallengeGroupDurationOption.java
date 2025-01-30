package site.dogether.challengegroup.domain;

import lombok.RequiredArgsConstructor;
import site.dogether.common.docs.RestDocsEnumType;

@RequiredArgsConstructor
public enum ChallengeGroupDurationOption implements RestDocsEnumType {

    THREE_DAYS(3, "3일"),
    SEVEN_DAYS(7, "7일"),
    FOURTEEN_DAYS(14, "14일"),
    TWENTY_EIGHT_DAYS(28, "28일")
    ;

    private final int days;
    private final String description;

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getValue() {
        return String.valueOf(this.days);
    }
}
