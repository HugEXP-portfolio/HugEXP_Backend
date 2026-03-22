package org.example.hugmeexp.global.infra.auth.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class AccessTokenStillValidException extends BaseException {
    public AccessTokenStillValidException() {
        super(ErrorStatus.ACCESS_TOKEN_STILL_VALID);
    }
}
