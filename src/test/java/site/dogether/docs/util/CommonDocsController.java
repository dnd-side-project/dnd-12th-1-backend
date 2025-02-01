package site.dogether.docs.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.dogether.docs.challengegroup.enumtype.ChallengeGroupDurationOptionDocs;
import site.dogether.docs.challengegroup.enumtype.ChallengeGroupStartAtOptionDocs;
import site.dogether.docs.dailytodocertification.enumtype.DailyTodoCertificationReviewResultDocs;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RequestMapping("/test")
@RestController
public class CommonDocsController {

    @GetMapping("/enums")
    public EnumDocs findEnums() {
        final Map<String, String> challengeGroupStartAtOption = convertToMap(ChallengeGroupStartAtOptionDocs.getValues());
        final Map<String, String> challengeGroupDurationOption = convertToMap(ChallengeGroupDurationOptionDocs.getValues());
        final Map<String, String> dailyTodoCertificationReviewResult = convertToMap(DailyTodoCertificationReviewResultDocs.getValues());
        return EnumDocs.builder()
                   .challengeGroupStartAtOption(challengeGroupStartAtOption)
                   .challengeGroupDurationOption(challengeGroupDurationOption)
                   .dailyTodoCertificationReviewResult(dailyTodoCertificationReviewResult)
                   .build();
    }

    private Map<String, String> convertToMap(final RestDocsEnumType[] restDocsEnumTypes) {
        return Arrays.stream(restDocsEnumTypes)
                     .collect(toMap(RestDocsEnumType::getRequestValue, RestDocsEnumType::getDescription));
    }
}
