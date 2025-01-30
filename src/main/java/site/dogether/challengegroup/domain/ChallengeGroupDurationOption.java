package site.dogether.challengegroup.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChallengeGroupDurationOption {

    THREE_DAYS(3),
    SEVEN_DAYS(7),
    FOURTEEN_DAYS(14),
    TWENTY_EIGHT_DAYS(28)
    ;

    private final int value;
}
