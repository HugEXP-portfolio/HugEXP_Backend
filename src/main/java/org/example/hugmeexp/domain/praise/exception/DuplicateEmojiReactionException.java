package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class DuplicateEmojiReactionException extends BaseException {
    public DuplicateEmojiReactionException() {
        super(ErrorStatus.DUPLICATE_EMOJI_REACTION);
    }
}
