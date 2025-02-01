package site.dogether.dailytodocertification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.dogether.common.controller.response.ApiResponse;
import site.dogether.dailytodocertification.controller.request.ReviewDailyTodoCertificationRequest;
import site.dogether.dailytodocertification.controller.response.GetDailyTodoCertificationForReviewByIdResponse;
import site.dogether.dailytodocertification.controller.response.GetDailyTodoCertificationsForReviewResponse;
import site.dogether.dailytodocertification.controller.response.DailyTodoCertificationResponse;

import java.util.List;

import static site.dogether.dailytodocertification.controller.response.DailyTodoCertificationSuccessCode.*;

@RequestMapping("/api/todo-certifications")
@RestController
public class DailyTodoCertificationController {

    @PostMapping("{todoCertificationId}/review")
    public ResponseEntity<ApiResponse<Void>> reviewDailyTodoCertification(
        @PathVariable Long todoCertificationId,
        @RequestBody final ReviewDailyTodoCertificationRequest request) {
        return ResponseEntity.ok(ApiResponse.success(REVIEW_DAILY_TODO_CERTIFICATION));
    }

    @GetMapping("/pending-review")
    public ResponseEntity<ApiResponse<GetDailyTodoCertificationsForReviewResponse>> getDailyTodoCertificationsForReview() {
        return ResponseEntity.ok(ApiResponse.successWithData(
            GET_DAILY_TODO_CERTIFICATIONS_FOR_REVIEW,
            new GetDailyTodoCertificationsForReviewResponse(
                List.of(
                    new DailyTodoCertificationResponse(
                        1L,
                        "나 진짜진짜 열심히 했어... ㄹㅇ...",
                        List.of(
                            "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/e1.png",
                            "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/e2.png"),
                        "유산소 & 무산소 1시간 조지기"),
                    new DailyTodoCertificationResponse(
                        2L,
                        "공부까지 갓벽...",
                        List.of(
                            "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/s1.png",
                            "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/s2.png"),
                        "공부 3시간 조지기")))));
    }

    @GetMapping("/pending-review/{todoCertificationId}")
    public ResponseEntity<ApiResponse<GetDailyTodoCertificationForReviewByIdResponse>> getDailyTodoCertificationForReviewById(@PathVariable Long todoCertificationId) {
        return ResponseEntity.ok(ApiResponse.successWithData(
            GET_DAILY_TODO_CERTIFICATION_FOR_REVIEW_BY_ID,
            new GetDailyTodoCertificationForReviewByIdResponse(
                new DailyTodoCertificationResponse(
                    1L,
                    "나 진짜진짜 열심히 했어... ㄹㅇ...",
                    List.of(
                        "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/e1.png",
                        "https://dogether-bucket-dev.s3.ap-northeast-2.amazonaws.com/daily-todo-proof-media/mock/e2.png"),
                    "유산소 & 무산소 1시간 조지기"))));
    }
}
