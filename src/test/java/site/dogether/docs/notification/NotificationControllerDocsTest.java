package site.dogether.docs.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import site.dogether.docs.util.RestDocsSupport;
import site.dogether.notification.controller.NotificationController;
import site.dogether.notification.controller.request.DeleteNotificationTokenRequest;
import site.dogether.notification.controller.request.SaveNotificationTokenRequest;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationControllerDocsTest extends RestDocsSupport {

    @Override
    protected Object initController() {
        return new NotificationController();
    }

    @DisplayName("푸시 알림 토큰 저장 API")
    @Test
    void saveNotificationToken() throws Exception {
        final SaveNotificationTokenRequest request = new SaveNotificationTokenRequest("kelly-token-value");

        mockMvc.perform(
                post("/api/notification/tokens")
                    .header("Authorization", "Bearer access_token")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(convertToJson(request)))
            .andExpect(status().isOk())
            .andDo(createDocument(
                requestFields(
                    fieldWithPath("token")
                        .description("저장할 푸시 알림 토큰")
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("FCM에서 발급받은 토큰만 입력 가능"))),
                responseFields(
                    fieldWithPath("code")
                        .description("응답 코드")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING))));
    }

    @DisplayName("푸시 알림 토큰 삭제 API")
    @Test
    void deleteNotificationToken() throws Exception {
        final DeleteNotificationTokenRequest request = new DeleteNotificationTokenRequest("kelly-token-value");

        mockMvc.perform(
                delete("/api/notification/tokens")
                    .header("Authorization", "Bearer access_token")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(convertToJson(request)))
            .andExpect(status().isOk())
            .andDo(createDocument(
                requestFields(
                    fieldWithPath("token")
                        .description("삭제할 푸시 알림 토큰")
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("서버에 저장된 FCM 토큰 입력"))),
                responseFields(
                    fieldWithPath("code")
                        .description("응답 코드")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING))));
    }
}
