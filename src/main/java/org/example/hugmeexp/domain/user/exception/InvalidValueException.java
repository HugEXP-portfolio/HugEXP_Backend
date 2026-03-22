package org.example.hugmeexp.domain.user.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class InvalidValueException extends BaseException {
    public InvalidValueException(String message) {
        super(ErrorStatus.INVALID_VALUE, message);
    }
}
