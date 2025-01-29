package site.dogether.challengegroup.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.dogether.common.controller.response.SuccessCode;

@RequiredArgsConstructor
@Getter
public enum ChallengeGroupSuccessCode implements SuccessCode {

    CREATE_CHALLENGE_GROUP("CGS-0001", "챌린지 그룹을 생성하였습니다."),
    JOIN_CHALLENGE_GROUP("CGS-0002", "챌린지 그룹에 참여하였습니다."),
    GET_JOINING_CHALLENGE_GROUP_INFO("CGS-0003", "참여중인 그룹 정보를 조회하였습니다."),
    GET_JOINING_CHALLENGE_GROUP_MY_ACTIVITY_SUMMARY("CGS-0004", "참여중인 그룹의 내 누적 활동 통계 정보를 조회하였습니다."),
    GET_JOINING_CHALLENGE_GROUP_TEAM_ACTIVITY_SUMMARY("CGS-0005", "참여중인 그룹의 팀 전체 누적 활동 통계 정보를 조회하였습니다."),
    ;

    private final String value;
    private final String message;
}
