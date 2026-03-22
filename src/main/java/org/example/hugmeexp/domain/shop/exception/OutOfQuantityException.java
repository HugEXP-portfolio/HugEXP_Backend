package org.example.hugmeexp.domain.shop.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class OutOfQuantityException extends BaseException {
    public OutOfQuantityException() {
        super(ErrorStatus.OUT_OF_QUANTITY);
    }
}
