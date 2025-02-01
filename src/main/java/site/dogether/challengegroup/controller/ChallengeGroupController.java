package site.dogether.challengegroup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.dogether.challengegroup.controller.request.CreateChallengeGroupRequest;
import site.dogether.challengegroup.controller.request.JoinChallengeGroupRequest;
import site.dogether.challengegroup.controller.response.CreateChallengeGroupResponse;
import site.dogether.challengegroup.controller.response.GetJoiningChallengeGroupInfoResponse;
import site.dogether.challengegroup.controller.response.GetJoiningChallengeGroupMyActivitySummaryResponse;
import site.dogether.challengegroup.controller.response.GetJoiningChallengeGroupTeamActivitySummaryResponse;
import site.dogether.common.controller.response.ApiResponse;

import java.util.List;

import static site.dogether.challengegroup.controller.response.ChallengeGroupSuccessCode.*;

@RequestMapping("/api/groups")
@RestController
public class ChallengeGroupController {

    @PostMapping
    public ResponseEntity<ApiResponse<CreateChallengeGroupResponse>> createChallengeGroup(
        @RequestBody final CreateChallengeGroupRequest request) {
        return ResponseEntity.ok(
            ApiResponse.successWithData(
                CREATE_CHALLENGE_GROUP,
                new CreateChallengeGroupResponse("kelly-join-code")));
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> joinChallengeGroup(
        @RequestBody final JoinChallengeGroupRequest request) {
        return ResponseEntity.ok(
            ApiResponse.success(JOIN_CHALLENGE_GROUP));
    }

    @GetMapping("/info/current")
    public ResponseEntity<ApiResponse<GetJoiningChallengeGroupInfoResponse>> getJoiningChallengeGroupInfo() {
        return ResponseEntity.ok(
            ApiResponse.successWithData(
                GET_JOINING_CHALLENGE_GROUP_INFO,
                new GetJoiningChallengeGroupInfoResponse("성욱이와 친구들", 7, 5)));
    }

    @GetMapping("/summary/my")
    public ResponseEntity<ApiResponse<GetJoiningChallengeGroupMyActivitySummaryResponse>> getJoiningChallengeGroupMyActivitySummary() {
        return ResponseEntity.ok(
            ApiResponse.successWithData(
                GET_JOINING_CHALLENGE_GROUP_MY_ACTIVITY_SUMMARY,
                new GetJoiningChallengeGroupMyActivitySummaryResponse(15, 10, 10)));
    }

    @GetMapping("/summary/team")
    public ResponseEntity<ApiResponse<GetJoiningChallengeGroupTeamActivitySummaryResponse>> getJoiningChallengeGroupTeamActivitySummary() {
        return ResponseEntity.ok(
            ApiResponse.successWithData(
                GET_JOINING_CHALLENGE_GROUP_TEAM_ACTIVITY_SUMMARY,
                new GetJoiningChallengeGroupTeamActivitySummaryResponse(
                    40, 30, 20,
                    List.of(
                        new GetJoiningChallengeGroupTeamActivitySummaryResponse.Rank(1, "양성욱", 100, 100),
                        new GetJoiningChallengeGroupTeamActivitySummaryResponse.Rank(2, "김영재", 82, 76),
                        new GetJoiningChallengeGroupTeamActivitySummaryResponse.Rank(3, "문지원", 68, 50)))));
    }
}
