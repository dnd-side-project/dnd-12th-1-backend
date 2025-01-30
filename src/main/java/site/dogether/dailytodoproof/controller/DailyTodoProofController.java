package site.dogether.dailytodoproof.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.dogether.common.controller.response.ApiResponse;
import site.dogether.dailytodoproof.controller.request.ReviewDailyTodoProofRequest;
import site.dogether.dailytodoproof.controller.response.GetDailyTodoProofForReviewByIdResponse;
import site.dogether.dailytodoproof.controller.response.GetDailyTodoProofsForReviewResponse;
import site.dogether.dailytodoproof.controller.response.DailyTodoProofResponse;

import java.util.List;

import static site.dogether.dailytodoproof.controller.response.DailyTodoProofSuccessCode.*;

@RequestMapping("/api/todo-proofs")
@RestController
public class DailyTodoProofController {

    @PostMapping("{todoProofId}/review")
    public ResponseEntity<ApiResponse<Void>> reviewDailyTodoProof(
        @PathVariable Long todoProofId,
        @RequestBody final ReviewDailyTodoProofRequest request) {
        return ResponseEntity.ok(ApiResponse.success(REVIEW_DAILY_TODO_PROOF));
    }

    @GetMapping("/pending-review")
    public ResponseEntity<ApiResponse<GetDailyTodoProofsForReviewResponse>> getDailyTodoProofsForReview() {
        return ResponseEntity.ok(ApiResponse.successWithData(
            GET_DAILY_TODO_PROOFS_FOR_REVIEW,
            new GetDailyTodoProofsForReviewResponse(
                List.of(
                    new DailyTodoProofResponse(
                        1L,
                        "나 진짜진짜 열심히 했어... ㄹㅇ...",
                        List.of(
                            "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/e1.png",
                            "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/e2.png"),
                        "유산소 & 무산소 1시간 조지기"),
                    new DailyTodoProofResponse(
                        2L,
                        "공부까지 갓벽...",
                        List.of(
                            "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/s1.png",
                            "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/s2.png"),
                        "공부 3시간 조지기")))));
    }

    @GetMapping("/pending-review/{todoProofId}")
    public ResponseEntity<ApiResponse<GetDailyTodoProofForReviewByIdResponse>> getDailyTodoProofForReviewById(@PathVariable Long todoProofId) {
        return ResponseEntity.ok(ApiResponse.successWithData(
            GET_DAILY_TODO_PROOF_FOR_REVIEW_BY_ID,
            new GetDailyTodoProofForReviewByIdResponse(
                new DailyTodoProofResponse(
                    1L,
                    "나 진짜진짜 열심히 했어... ㄹㅇ...",
                    List.of(
                        "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/e1.png",
                        "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/e2.png"),
                    "유산소 & 무산소 1시간 조지기"))));
    }
}
