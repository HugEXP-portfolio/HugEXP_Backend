package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UserNotFoundInPraiseException extends BaseException {
    public UserNotFoundInPraiseException() {
        super(ErrorStatus.USER_NOT_FOUND_IN_PRAISE);
    }
}
