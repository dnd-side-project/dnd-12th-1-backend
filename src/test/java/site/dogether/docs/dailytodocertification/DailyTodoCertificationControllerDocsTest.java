package site.dogether.docs.dailytodocertification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import site.dogether.dailytodocertification.controller.DailyTodoCertificationController;
import site.dogether.dailytodocertification.controller.request.ReviewDailyTodoCertificationRequest;
import site.dogether.docs.util.RestDocsSupport;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static site.dogether.docs.util.DocumentLinkGenerator.*;
import static site.dogether.docs.util.DocumentLinkGenerator.DocUrl.*;

@DisplayName("데일리 투두 수행 인증 API 문서화 테스트")
public class DailyTodoCertificationControllerDocsTest extends RestDocsSupport {

    @Override
    protected Object initController() {
        return new DailyTodoCertificationController();
    }

    @DisplayName("데일리 투두 수행 인증 검사 API")
    @Test
    void reviewDailyTodoCertification() throws Exception {
        final long todoCertificationId = 1L;
        final ReviewDailyTodoCertificationRequest request = new ReviewDailyTodoCertificationRequest(
            "REJECT",
            "그게 정말 최선이야?"
        );

        mockMvc.perform(
                post("/api/todo-certifications/{todoCertificationId}/review", todoCertificationId)
                    .header("Authorization", "Bearer access_token")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(convertToJson(request)))
            .andExpect(status().isOk())
            .andDo(createDocument(
                pathParameters(
                    parameterWithName("todoCertificationId")
                        .description("데일리 투두 인증 id")
                        .attributes(constraints("등록된 데일리 투두 인증 id만 입력 가능"), pathVariableExample(todoCertificationId))),
                requestFields(
                    fieldWithPath("result")
                        .description(generateLink(DAILY_TODO_CERTIFICATION_REVIEW_RESULT))
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("정해진 값만 입력 허용")),
                    fieldWithPath("rejectReason")
                        .description("노인정 사유")
                        .type(JsonFieldType.STRING)
                        .optional()
                        .attributes(constraints("노인정일 경우에만 사유 입력, 인정과 함께 해당 필드의 데이터를 보내면 해당 데이터는 무시됨."))),
                responseFields(
                    fieldWithPath("code")
                        .description("응답 코드")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING))));
    }
    
    @DisplayName("검사할 투두 수행 인증 전체 조회 API")
    @Test        
    void getDailyTodoCertificationsForReview() throws Exception {
        mockMvc.perform(
                get("/api/todo-certifications/pending-review")
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
                    fieldWithPath("data.dailyTodoCertifications")
                        .description("검사할 투두 수행 인증 리스트")
                        .type(JsonFieldType.ARRAY)
                        .optional(),
                    fieldWithPath("data.dailyTodoCertifications[].id")
                        .description("투두 수행 인증 id")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.dailyTodoCertifications[].content")
                        .description("수행 인증 본문")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("data.dailyTodoCertifications[].mediaUrls")
                        .description("인증에 포함된 미디어 리소스")
                        .type(JsonFieldType.ARRAY)
                        .optional(),
                    fieldWithPath("data.dailyTodoCertifications[].todoContent")
                        .description("수행 인증한 투두 내용")
                        .type(JsonFieldType.STRING)
                )));
    }

    @DisplayName("검사할 특정 투두 수행 인증 상세 조회 API")
    @Test
    void getDailyTodoCertificationForReviewById() throws Exception {
        final long todoCertificationId = 1L;

        mockMvc.perform(
                get("/api/todo-certifications/pending-review/{todoCertificationId}", todoCertificationId)
                    .header("Authorization", "Bearer access_token")
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andDo(createDocument(
                pathParameters(
                    parameterWithName("todoCertificationId")
                        .description("데일리 투두 인증 id")
                        .attributes(constraints("등록된 데일리 투두 인증 id만 입력 가능"), pathVariableExample(todoCertificationId))),
                responseFields(
                    fieldWithPath("code")
                        .description("응답 코드")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("data.id")
                        .description("투두 수행 인증 id")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.content")
                        .description("수행 인증 본문")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("data.mediaUrls")
                        .description("인증에 포함된 미디어 리소스")
                        .type(JsonFieldType.ARRAY)
                        .optional(),
                    fieldWithPath("data.todoContent")
                        .description("수행 인증한 투두 내용")
                        .type(JsonFieldType.STRING)
                )));
    }
}
