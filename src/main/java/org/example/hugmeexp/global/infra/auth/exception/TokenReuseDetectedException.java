package org.example.hugmeexp.global.infra.auth.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class TokenReuseDetectedException extends BaseException {
    public TokenReuseDetectedException() {
        super(ErrorStatus.TOKEN_REUSE_DETECTED);
    }
}
