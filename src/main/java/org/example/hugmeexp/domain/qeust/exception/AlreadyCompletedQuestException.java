package org.example.hugmeexp.domain.qeust.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class AlreadyCompletedQuestException extends BaseException {
    public AlreadyCompletedQuestException(Long questId) {
        super(ErrorStatus.ALREADY_COMPLETED_QUEST, questId);
    }
}
