package org.example.hugmeexp.domain.qeust.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class QuestDeletedException extends BaseException {
    public QuestDeletedException() {
        super(ErrorStatus.QUEST_DELETED);
    }
}
