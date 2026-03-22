package org.example.hugmeexp.domain.user.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class PhoneNumberDuplicatedException extends BaseException {
    public PhoneNumberDuplicatedException() {
        super(ErrorStatus.PHONE_NUMBER_DUPLICATED);
    }
}
