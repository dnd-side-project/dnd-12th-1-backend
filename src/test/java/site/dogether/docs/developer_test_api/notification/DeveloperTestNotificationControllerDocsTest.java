package site.dogether.docs.developer_test_api.notification;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import site.dogether.developer_test_api.notification.DeveloperTestNotificationController;
import site.dogether.developer_test_api.notification.SendNotificationRequest;
import site.dogether.docs.util.RestDocsSupport;
import site.dogether.notification.infrastructure.firebase.sender.FcmNotificationSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("개발자 테스트용 푸시 알림 API 문서화 테스트")
public class DeveloperTestNotificationControllerDocsTest extends RestDocsSupport {

    private final FcmNotificationSender fcmNotificationSender = mock(FcmNotificationSender.class);

    @Override
    protected Object initController() {
        return new DeveloperTestNotificationController(fcmNotificationSender);
    }

    @DisplayName("개발자 테스트용 푸시 알림 전송 API")
    @Test
    void sendNotification() throws Exception {
        final SendNotificationRequest request = new SendNotificationRequest("client-fcm-token-value", "푸시 알림 제목", "푸시 알림 바디");

        doNothing().when(fcmNotificationSender).send(any());

        mockMvc.perform(
                post("/api/send-notification")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(convertToJson(request)))
            .andExpect(status().isOk())
            .andDo(createDocument(
                requestFields(
                    fieldWithPath("token")
                        .description("클라이언트 FCM 토큰")
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("FCM 토큰이 유효하지 않으면 푸시 알림이 오지 않음. 백엔드 팀에 문의.")),
                    fieldWithPath("title")
                        .description("푸시 알림 제목")
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("공백 X")),
                    fieldWithPath("body")
                        .description("푸시 알림 본문")
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("공백 X"))),
                responseFields(
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING))));
    }
}
