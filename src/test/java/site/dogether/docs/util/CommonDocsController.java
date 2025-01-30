package site.dogether.docs.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.dogether.docs.challengegroup.enumtype.ChallengeGroupDurationOptionDocs;
import site.dogether.docs.challengegroup.enumtype.ChallengeGroupStartAtOptionDocs;
import site.dogether.docs.dailytodoproof.enumtype.DailyTodoProofReviewResultDocs;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RequestMapping("/test")
@RestController
public class CommonDocsController {

    @GetMapping("/enums")
    public EnumDocs findEnums() {
        final Map<String, String> challengeGroupStartAtOption = convertToMap(ChallengeGroupStartAtOptionDocs.values());
        final Map<String, String> challengeGroupDurationOption = convertToMap(ChallengeGroupDurationOptionDocs.values());
        final Map<String, String> dailyTodoProofReviewResult = convertToMap(DailyTodoProofReviewResultDocs.values());
        return new EnumDocs(challengeGroupStartAtOption, challengeGroupDurationOption, dailyTodoProofReviewResult);
    }

    private Map<String, String> convertToMap(final RestDocsEnumType[] restDocsEnumTypes) {
        return Arrays.stream(restDocsEnumTypes)
                     .collect(toMap(RestDocsEnumType::getRequestValue, RestDocsEnumType::getDescription));
    }
}
