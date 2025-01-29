package site.dogether.challengegroup.controller.response;

public record GetJoiningChallengeGroupInfoResponse(
    String groupName,
    int memberCount,
    int dailyTodoLimit
) {
}
