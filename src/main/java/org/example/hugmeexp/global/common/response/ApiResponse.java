package org.example.hugmeexp.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.example.hugmeexp.global.common.exception.code.BaseCode;

@Getter
@JsonPropertyOrder({"isSuccess", "message", "data"})
public class ApiResponse<T> {

    private final boolean isSuccess;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    private ApiResponse(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    // 성공 응답 (데이터 + 메시지)
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    // 성공 응답 (메시지만)
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null);
    }

    // 성공 응답 (BaseCode 사용)
    public static <T> ApiResponse<T> success(BaseCode code, T data) {
        return new ApiResponse<>(true, code.getMessage(), data);
    }

    // 실패 응답 (BaseCode 사용)
    public static <T> ApiResponse<T> failure(BaseCode code, T data) {
        return new ApiResponse<>(false, code.getMessage(), data);
    }

    // 실패 응답 (메시지 직접 지정)
    public static <T> ApiResponse<T> failure(String message, T data) {
        return new ApiResponse<>(false, message, data);
    }

    // 실패 응답 (메시지만)
    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
