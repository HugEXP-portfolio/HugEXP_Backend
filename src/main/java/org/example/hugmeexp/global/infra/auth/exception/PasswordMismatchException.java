package org.example.hugmeexp.global.infra.auth.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class PasswordMismatchException extends BaseException {
    public PasswordMismatchException() {
        super(ErrorStatus.PASSWORD_MISMATCH);
    }
}
