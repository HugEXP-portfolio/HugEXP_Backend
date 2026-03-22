package org.example.hugmeexp.domain.qeust.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class QuestNotCompletableException extends BaseException {
    public QuestNotCompletableException() {
        super(ErrorStatus.QUEST_NOT_COMPLETABLE);
    }
}
