package site.dogether.dailytodoproof.controller.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public record GetDailyTodoProofForReviewByIdResponse(@JsonUnwrapped DailyTodoProofResponse todoProof) {
}
