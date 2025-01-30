package site.dogether.docs.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsSupport {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected MockMvc mockMvc;

    protected static Attributes.Attribute constraints(final String value) {
        return new Attributes.Attribute("constraints", value);
    }

    protected static Attributes.Attribute pathVariableExample(final long value) {
        return new Attributes.Attribute("pathVariableExample", value);
    }

    @BeforeEach
    void setUp(final RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
                .apply(documentationConfiguration(provider)
                    .operationPreprocessors()
                    .withRequestDefaults(
                        modifyHeaders()
                            .remove("Content-Length")
                            .remove("Host"),
                        prettyPrint())
                    .withResponseDefaults(
                        modifyHeaders()
                            .remove("Content-Length")
                            .remove("X-Content-Type-Options")
                            .remove("X-XSS-Protection")
                            .remove("Cache-Control")
                            .remove("Pragma")
                            .remove("Expires")
                            .remove("X-Frame-Options"),
                        prettyPrint()))
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    protected RestDocumentationResultHandler createDocument(final Snippet... snippets) {
        return document(
            "{class-name}/{method-name}",
            getDocumentRequest(),
            preprocessResponse(),
            snippets
        );
    }

    protected OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(new UriDecodingPreprocessor());
    }

    protected String convertToJson(final Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    protected abstract Object initController();
}
