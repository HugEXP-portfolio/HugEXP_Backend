package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class InvalidPraiseEmojiAccessException extends BaseException {
    public InvalidPraiseEmojiAccessException() {
        super(ErrorStatus.INVALID_PRAISE_EMOJI_ACCESS);
    }
}
