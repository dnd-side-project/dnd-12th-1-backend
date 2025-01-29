package site.dogether.challengegroup.domain;

import lombok.RequiredArgsConstructor;
import site.dogether.common.constant.EnumType;

@RequiredArgsConstructor
public enum ChallengeGroupStartAtOption implements EnumType {

    TODAY("오늘부터"),
    TOMORROW("내일부터")
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
