package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UnauthorizedEmojiDeleteException extends BaseException {
    public UnauthorizedEmojiDeleteException() {
        super(ErrorStatus.UNAUTHORIZED_EMOJI_DELETE);
    }
}
