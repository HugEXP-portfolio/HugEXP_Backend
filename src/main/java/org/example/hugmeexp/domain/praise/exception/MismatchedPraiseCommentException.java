package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class MismatchedPraiseCommentException extends BaseException {
    public MismatchedPraiseCommentException() {
        super(ErrorStatus.MISMATCHED_PRAISE_COMMENT);
    }
}
