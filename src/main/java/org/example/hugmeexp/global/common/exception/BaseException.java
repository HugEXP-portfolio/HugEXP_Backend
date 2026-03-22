package org.example.hugmeexp.global.common.exception;

import lombok.Getter;
import org.example.hugmeexp.global.common.exception.code.BaseCode;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

/**
 * 어플리케이션의 모든 커스텀 예외에 대한 최상위 부모 클래스
 */
@Getter
public class BaseException extends RuntimeException {

    private final BaseCode errorCode;

    public BaseException(BaseCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(BaseCode errorCode, Object... args) {
        super(String.format(errorCode.getMessage(), args));
        this.errorCode = errorCode;
    }

    /**
     * 커스텀 메시지를 직접 지정하는 생성자 (ErrorStatus의 기본 메시지를 대체).
     * 파라미터 순서를 반대로 하여 varargs 생성자와 구별합니다.
     */
    protected BaseException(String customMessage, BaseCode errorCode) {
        super(ErrorStatus.PREFIX + customMessage);
        this.errorCode = errorCode;
    }
}
