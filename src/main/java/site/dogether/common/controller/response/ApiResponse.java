package site.dogether.common.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import site.dogether.common.error.ExceptionCode;

@Getter
public class ApiResponse<T> {

    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public static ApiResponse<Void> success(final SuccessCode code) {
        return new ApiResponse<>(code.getValue(), code.getMessage(), null);
    }

    public static <T> ApiResponse<T> successWithData(final SuccessCode code, final T data) {
        return new ApiResponse<>(code.getValue(), code.getMessage(), data);
    }

    public static ApiResponse<Void> fail(final ExceptionCode exceptionCode) {
        return new ApiResponse<>(exceptionCode.getValue(), exceptionCode.getMessage(), null);
    }

    private ApiResponse(
        final String code,
        final String message,
        final T data
    ) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
