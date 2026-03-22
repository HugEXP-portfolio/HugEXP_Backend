package org.example.hugmeexp.global.infra.auth.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class InvalidRefreshTokenException extends BaseException {
    public InvalidRefreshTokenException() {
        super(ErrorStatus.INVALID_REFRESH_TOKEN);
    }
}
