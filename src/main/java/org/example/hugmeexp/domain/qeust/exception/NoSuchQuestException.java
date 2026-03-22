package org.example.hugmeexp.domain.qeust.exception;

import org.example.hugmeexp.domain.user.entity.User;
import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class NoSuchQuestException extends BaseException {
    public NoSuchQuestException(User user, Long userQuestId) {
        super(ErrorStatus.NO_SUCH_QUEST, user.getUsername(), userQuestId);
    }
}
