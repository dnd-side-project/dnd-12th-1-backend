package site.dogether.challengegroup.controller.response;

import java.util.List;

public record GetJoiningChallengeGroupTeamActivitySummaryResponse(
    int totalTodoCount,
    int totalCertificatedCount,
    int totalApprovedCount,
    List<Rank> ranking
) {
    public record Rank(
        int rank,
        String name
    ) {}
}
