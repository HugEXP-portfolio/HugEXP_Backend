package org.example.hugmeexp.domain.qeust.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UserNotFoundInQuestException extends BaseException {
    public UserNotFoundInQuestException(String username) {
        super(ErrorStatus.USER_NOT_FOUND_IN_QUEST, username);
    }
}
