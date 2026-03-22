package org.example.hugmeexp.global.infra.auth.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class LoginFailedException extends BaseException {
    public LoginFailedException() {
        super(ErrorStatus.LOGIN_FAILED);
    }
}
