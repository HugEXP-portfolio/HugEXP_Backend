package org.example.hugmeexp.domain.qeust.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class QuestNotFoundException extends BaseException {
    public QuestNotFoundException(Long questId) {
        super(ErrorStatus.QUEST_NOT_FOUND, questId);
    }
}
