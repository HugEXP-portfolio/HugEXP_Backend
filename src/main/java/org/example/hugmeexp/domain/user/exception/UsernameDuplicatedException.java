package org.example.hugmeexp.domain.user.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UsernameDuplicatedException extends BaseException {
    public UsernameDuplicatedException() {
        super(ErrorStatus.USERNAME_DUPLICATED);
    }
}
