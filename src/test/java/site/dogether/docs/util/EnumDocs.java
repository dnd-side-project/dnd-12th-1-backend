package site.dogether.docs.util;

import java.util.Map;

public class EnumDocs {

    Map<String, String> challengeGroupStartAtOption;
    Map<String, String> challengeGroupDurationOption;
    Map<String, String> dailyTodoProofReviewResult;

    public EnumDocs() {
    }

    public EnumDocs(
        final Map<String, String> challengeGroupStartAtOption,
        final Map<String, String> challengeGroupDurationOption,
        final Map<String, String> dailyTodoProofReviewResult
    ) {
        this.challengeGroupStartAtOption = challengeGroupStartAtOption;
        this.challengeGroupDurationOption = challengeGroupDurationOption;
        this.dailyTodoProofReviewResult = dailyTodoProofReviewResult;
    }

    public Map<String, String> getChallengeGroupStartAtOption() {
        return challengeGroupStartAtOption;
    }

    public Map<String, String> getChallengeGroupDurationOption() {
        return challengeGroupDurationOption;
    }

    public Map<String, String> getDailyTodoProofReviewResult() {
        return dailyTodoProofReviewResult;
    }
}
