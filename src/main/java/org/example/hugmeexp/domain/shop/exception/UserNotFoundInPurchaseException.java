package org.example.hugmeexp.domain.shop.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UserNotFoundInPurchaseException extends BaseException {
    public UserNotFoundInPurchaseException() {
        super(ErrorStatus.USER_NOT_FOUND_IN_PURCHASE);
    }
}
