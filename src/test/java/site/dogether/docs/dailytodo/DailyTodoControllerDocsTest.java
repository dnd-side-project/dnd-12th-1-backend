package site.dogether.docs.dailytodo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import site.dogether.dailytodo.controller.DailyTodoController;
import site.dogether.dailytodo.controller.request.CreateDailyTodosRequest;
import site.dogether.docs.util.RestDocsSupport;

import java.util.List;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("데일리 투두 API 문서화 테스트")
public class DailyTodoControllerDocsTest extends RestDocsSupport {

    @Override
    protected Object initController() {
        return new DailyTodoController();
    }

    @DisplayName("데일리 투두 작성 API")
    @Test        
    void createDailyTodos() throws Exception {
        final CreateDailyTodosRequest request = new CreateDailyTodosRequest(List.of(
            "프로그래머스 코테 두 문제 풀기",
            "저녁 운동 조지기",
            "감정 회고록 작성하기"
        ));

        mockMvc.perform(
                post("/api/todos")
                    .header("Authorization", "Bearer access_token")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(convertToJson(request)))
            .andExpect(status().isOk())
            .andDo(createDocument(
                requestFields(
                    fieldWithPath("todos")
                        .description("데일리 투두 리스트")
                        .type(JsonFieldType.ARRAY)
                        .attributes(constraints(
                            "그룹장 외 참여한 사람이 없다면 투두 작성 불가, " +
                                "투두 개수는 2 ~ 그룹에서 정한 하루 최대 제한 개수 이하, " +
                                "투두 항목은 2 ~ 20 길이 문자열"))),
                responseFields(
                    fieldWithPath("code")
                        .description("응답 코드")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING))));
    }
    
    @DisplayName("어제 작성한 투두 내용 조회 API")
    @Test        
    void getYesterdayDailyTodos() throws Exception {
        mockMvc.perform(
                get("/api/todos/my/yesterday")
                    .header("Authorization", "Bearer access_token")
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(createDocument(
                responseFields(
                    fieldWithPath("code")
                        .description("응답 코드")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("data.todos")
                        .description("어제 작성한 투두 내용 리스트")
                        .optional()
                        .type(JsonFieldType.ARRAY)
                )));
    }
}
