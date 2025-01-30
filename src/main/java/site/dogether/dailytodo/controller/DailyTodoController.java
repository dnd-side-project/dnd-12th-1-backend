package site.dogether.dailytodo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.dogether.common.controller.response.ApiResponse;
import site.dogether.dailytodo.controller.request.CreateDailyTodosRequest;
import site.dogether.dailytodo.controller.request.GetYesterdayDailyTodosResponse;

import java.util.List;

import static site.dogether.dailytodo.controller.response.DailyTodoSuccessCode.CREATE_DAILY_TODOS;
import static site.dogether.dailytodo.controller.response.DailyTodoSuccessCode.GET_YESTERDAY_DAILY_TODOS;

@RequestMapping("/api/todos")
@RestController()
public class DailyTodoController {

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createDailyTodos(
        @RequestBody final CreateDailyTodosRequest request) {
        return ResponseEntity.ok(ApiResponse.success(CREATE_DAILY_TODOS));
    }

    /**
     * 클라이언트 미디어 전달 방식이 결정되면 작성
     */
//    @PostMapping("/{todoId}/certificate")
//    public ResponseEntity<ApiResponse<>>

    @GetMapping("/my/yesterday")
    public ResponseEntity<ApiResponse<GetYesterdayDailyTodosResponse>> getYesterdayDailyTodos() {
        return ResponseEntity.ok(ApiResponse.successWithData(
            GET_YESTERDAY_DAILY_TODOS,
            new GetYesterdayDailyTodosResponse(
                List.of(
                    "푸쉬업 10회",
                    "프로그래머스 코테 두 문제 풀기",
                    "스프링 강의 3개 듣기"))));
    }
}
