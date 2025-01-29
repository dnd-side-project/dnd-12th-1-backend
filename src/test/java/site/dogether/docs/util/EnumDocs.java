package site.dogether.docs.util;

import java.util.Map;

public class EnumDocs {

    Map<String, String> challengeGroupStartAtOption;
    Map<String, String> challengeGroupDurationOption;

    public EnumDocs() {
    }

    public EnumDocs(
        final Map<String, String> challengeGroupStartAtOption,
        final Map<String, String> challengeGroupDurationOption
    ) {
        this.challengeGroupStartAtOption = challengeGroupStartAtOption;
        this.challengeGroupDurationOption = challengeGroupDurationOption;
    }

    public Map<String, String> getChallengeGroupStartAtOption() {
        return challengeGroupStartAtOption;
    }

    public Map<String, String> getChallengeGroupDurationOption() {
        return challengeGroupDurationOption;
    }
}
