package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class CommentNotFoundException extends BaseException {
    public CommentNotFoundException() {
        super(ErrorStatus.PRAISE_COMMENT_NOT_FOUND);
    }
}
