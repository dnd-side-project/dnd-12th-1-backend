package site.dogether.docs.challengegroup;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import site.dogether.challengegroup.controller.ChallengeGroupController;
import site.dogether.challengegroup.controller.request.CreateChallengeGroupRequest;
import site.dogether.challengegroup.controller.request.JoinChallengeGroupRequest;
import site.dogether.docs.util.RestDocsSupport;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static site.dogether.docs.util.DocumentLinkGenerator.DocUrl.CHALLENGE_GROUP_DURATION_OPTION;
import static site.dogether.docs.util.DocumentLinkGenerator.DocUrl.CHALLENGE_GROUP_START_AT_OPTION;
import static site.dogether.docs.util.DocumentLinkGenerator.generateLink;

@DisplayName("챌린지 그룹 API 문서화 테스트")
public class ChallengeGroupControllerDocsTest extends RestDocsSupport {

    @Override
    protected Object initController() {
        return new ChallengeGroupController();
    }

    @DisplayName("챌린지 그룹 생성 API")
    @Test
    void createChallengeGroup() throws Exception {
        final CreateChallengeGroupRequest request = new CreateChallengeGroupRequest(
            "성욱이와 친구들",
            7,
            "TODAY",
            7,
            5
        );

        mockMvc.perform(
                post("/api/groups")
                    .header("Authorization", "Bearer access_token")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(convertToJson(request)))
            .andExpect(status().isOk())
            .andDo(createDocument(
                requestFields(
                    fieldWithPath("groupName")
                        .description("그룹명")
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("1 ~ 20 길이 문자열")),
                    fieldWithPath("memberCount")
                        .description("참여 가능 인원수")
                        .type(JsonFieldType.NUMBER)
                        .attributes(constraints("2 ~ 20 범위 정수")),
                    fieldWithPath("startAt")
                        .description(generateLink(CHALLENGE_GROUP_START_AT_OPTION))
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("정해진 값만 입력 허용")),
                    fieldWithPath("challengeDuration")
                        .description(generateLink(CHALLENGE_GROUP_DURATION_OPTION))
                        .type(JsonFieldType.NUMBER)
                        .attributes(constraints("정해진 값만 입력 허용")),
                    fieldWithPath("dailyTodoLimit")
                        .description("하루 최대 작성 가능 투두 개수")
                        .type(JsonFieldType.NUMBER)
                        .attributes(constraints("2 ~ 10 범위 정수"))
                ),
                responseFields(
                    fieldWithPath("code")
                        .description("응답 코드")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("data.joinCode")
                        .description("그룹 참여 코드")
                        .type(JsonFieldType.STRING))));
    }

    @DisplayName("챌린지 그룹 참가 API")
    @Test
    void joinChallengeGroup() throws Exception {
        final JoinChallengeGroupRequest request = new JoinChallengeGroupRequest("kelly-join-code");

        mockMvc.perform(
                post("/api/groups/join")
                    .header("Authorization", "Bearer access_token")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(convertToJson(request)))
            .andExpect(status().isOk())
            .andDo(createDocument(
                requestFields(
                    fieldWithPath("joinCode")
                        .description("그룹 참여 코드")
                        .type(JsonFieldType.STRING)
                        .attributes(constraints("서버에서 발급한 코드만 사용 갸능"))),
                responseFields(
                    fieldWithPath("code")
                        .description("응답 코드")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("message")
                        .description("응답 메시지")
                        .type(JsonFieldType.STRING))));
    }
    
    @DisplayName("참여중인 그룹 정보 조회 API")
    @Test        
    void getJoiningChallengeGroupInfo() throws Exception {
        mockMvc.perform(
                get("/api/groups/info/current")
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
                    fieldWithPath("data.groupName")
                        .description("그룹명")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("data.memberCount")
                        .description("그룹 인원수")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.dailyTodoLimit")
                        .description("하루 최대 작성 가능 투두 개수")
                        .type(JsonFieldType.NUMBER))));
    }
    
    @DisplayName("참여중인 그룹의 내 누적 활동 통계 조회 API")
    @Test        
    void getJoiningChallengeGroupMyActivitySummary() throws Exception {
        mockMvc.perform(
                get("/api/groups/summary/my")
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
                    fieldWithPath("data.totalTodoCount")
                        .description("작성한 전체 투두 개수")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.totalCertificatedCount")
                        .description("인증한 전체 투두 개수")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.totalApprovedCount")
                        .description("인정받은 전체 투두 개수")
                        .type(JsonFieldType.NUMBER))));
    }
    
    @DisplayName("참여중인 그룹의 팀원 전체 누적 활동 통계 조회 API")
    @Test        
    void getJoiningChallengeGroupTeamActivitySummary() throws Exception {
        mockMvc.perform(
                get("/api/groups/summary/team")
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
                    fieldWithPath("data.totalTodoCount")
                        .description("작성한 전체 투두 개수")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.totalCertificatedCount")
                        .description("인증한 전체 투두 개수")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.totalApprovedCount")
                        .description("인정받은 전체 투두 개수")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.ranking")
                        .description("그룹 내 활동 순위")
                        .type(JsonFieldType.ARRAY)
                        .optional(),
                    fieldWithPath("data.ranking[].rank")
                        .description("순위")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.ranking[].name")
                        .description("그룹원 이름")
                        .type(JsonFieldType.STRING),
                    fieldWithPath("data.ranking[].certificationRate")
                        .description("데일리 투두 인증률")
                        .type(JsonFieldType.NUMBER),
                    fieldWithPath("data.ranking[].approvalRate")
                        .description("데일리 투두 인정률")
                        .type(JsonFieldType.NUMBER))));
    }
}
