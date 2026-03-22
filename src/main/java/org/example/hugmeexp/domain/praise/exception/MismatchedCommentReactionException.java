package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class MismatchedCommentReactionException extends BaseException {
    public MismatchedCommentReactionException() {
        super(ErrorStatus.MISMATCHED_COMMENT_REACTION);
    }
}
