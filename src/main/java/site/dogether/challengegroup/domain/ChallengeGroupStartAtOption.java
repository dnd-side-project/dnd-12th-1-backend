package site.dogether.challengegroup.domain;

import lombok.RequiredArgsConstructor;
import site.dogether.common.docs.RestDocsEnumType;

@RequiredArgsConstructor
public enum ChallengeGroupStartAtOption implements RestDocsEnumType {

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
