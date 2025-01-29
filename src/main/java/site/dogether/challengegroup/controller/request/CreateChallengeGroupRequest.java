package site.dogether.challengegroup.controller.request;

public record CreateChallengeGroupRequest(
    String groupName,
    int memberCount,
    String startAt,
    int challengeDuration,
    int dailyTodoLimit
) {
}
