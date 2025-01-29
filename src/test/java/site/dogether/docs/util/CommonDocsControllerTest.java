package site.dogether.docs.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("API 문서화 util 테스트")
public class CommonDocsControllerTest extends RestDocsSupport {

    @Override
    protected Object initController() {
        return new CommonDocsController();
    }

    @Test
    void enums() throws Exception {
        final ResultActions result = this.mockMvc.perform(
                get("/test/enums")
                    .contentType(MediaType.APPLICATION_JSON));
        final MvcResult mvcResult = result.andReturn();
        final EnumDocs enumDocs = parseEnumDocs(mvcResult);

        result.andExpect(status().isOk())
            .andDo(createDocument(
                customResponseFields("custom-response", beneathPath("challengeGroupStartAtOption"),
                    attributes(key("title").value("challengeGroupStartAtOption")),
                    convertEnumToFieldDescriptor((enumDocs.getChallengeGroupStartAtOption()))),
                customResponseFields("custom-response", beneathPath("challengeGroupDurationOption"),
                    attributes(key("title").value("challengeGroupDurationOption")),
                    convertEnumToFieldDescriptor((enumDocs.getChallengeGroupDurationOption())))
            ));
    }

    public static CustomResponseFieldsSnippet customResponseFields(
        final String type,
        final PayloadSubsectionExtractor<?> subsectionExtractor,
        final Map<String, Object> attributes,
        final FieldDescriptor... descriptors
    ) {
        return new CustomResponseFieldsSnippet(
            type,
            subsectionExtractor,
            Arrays.asList(descriptors),
            attributes,
            true);
    }

    private static FieldDescriptor[] convertEnumToFieldDescriptor(final Map<String, String> enumValues) {
        return enumValues.entrySet().stream()
                   .map(x -> PayloadDocumentation
                                 .fieldWithPath(x.getKey())
                                 .description(x.getValue()))
                   .toArray(FieldDescriptor[]::new);
    }

    private EnumDocs parseEnumDocs(final MvcResult result) throws IOException {
        return objectMapper.readValue(
                result.getResponse().getContentAsByteArray(),
                new TypeReference<>() {});
    }
}
