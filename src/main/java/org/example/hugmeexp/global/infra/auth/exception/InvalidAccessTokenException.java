package org.example.hugmeexp.global.infra.auth.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class InvalidAccessTokenException extends BaseException {
    public InvalidAccessTokenException() {
        super(ErrorStatus.INVALID_ACCESS_TOKEN);
    }
}
