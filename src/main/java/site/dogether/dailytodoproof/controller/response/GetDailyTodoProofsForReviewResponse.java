package site.dogether.dailytodoproof.controller.response;

import java.util.List;

public record GetDailyTodoProofsForReviewResponse(List<DailyTodoProofResponse> todoProofs) {
}
