package site.dogether.docs.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.dogether.challengegroup.domain.ChallengeGroupDurationOption;
import site.dogether.challengegroup.domain.ChallengeGroupStartAtOption;
import site.dogether.common.constant.EnumType;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@RequestMapping("/test")
@RestController
public class CommonDocsController {

    @GetMapping("/enums")
    public EnumDocs findEnums() {
        final Map<String, String> challengeGroupStartAtOption = convertToMap(ChallengeGroupStartAtOption.values());
        final Map<String, String> challengeGroupDurationOption = convertToMap(ChallengeGroupDurationOption.values());
        return new EnumDocs(challengeGroupStartAtOption, challengeGroupDurationOption);
    }

    private Map<String, String> convertToMap(final EnumType[] enumTypes) {
        return Arrays.stream(enumTypes)
                     .collect(toMap(EnumType::getValue, EnumType::getDescription));
    }
}
