package org.example.hugmeexp.domain.shop.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class NotEnoughPointException extends BaseException {
    public NotEnoughPointException() {
        super(ErrorStatus.NOT_ENOUGH_POINT);
    }
}
