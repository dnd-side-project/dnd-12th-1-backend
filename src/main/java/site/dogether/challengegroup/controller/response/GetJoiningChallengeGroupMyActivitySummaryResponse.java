package site.dogether.challengegroup.controller.response;

public record GetJoiningChallengeGroupMyActivitySummaryResponse(
    int totalTodoCount,
    int totalCertificatedCount,
    int totalApprovedCount
) {
}
