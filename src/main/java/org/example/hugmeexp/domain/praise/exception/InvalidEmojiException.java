package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class InvalidEmojiException extends BaseException {
    public InvalidEmojiException() {
        super(ErrorStatus.INVALID_EMOJI);
    }
}
