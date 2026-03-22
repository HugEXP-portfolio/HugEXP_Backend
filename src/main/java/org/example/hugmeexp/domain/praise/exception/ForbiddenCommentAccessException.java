package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class ForbiddenCommentAccessException extends BaseException {
    public ForbiddenCommentAccessException() {
        super(ErrorStatus.FORBIDDEN_COMMENT_ACCESS);
    }
}
