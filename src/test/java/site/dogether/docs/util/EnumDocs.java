package site.dogether.docs.util;

import lombok.*;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnumDocs {

    Map<String, String> challengeGroupStartAtOption;
    Map<String, String> challengeGroupDurationOption;
    Map<String, String> dailyTodoCertificationReviewResult;
}
