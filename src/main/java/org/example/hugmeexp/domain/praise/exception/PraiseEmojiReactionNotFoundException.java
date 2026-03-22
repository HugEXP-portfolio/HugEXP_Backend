package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class PraiseEmojiReactionNotFoundException extends BaseException {
    public PraiseEmojiReactionNotFoundException() {
        super(ErrorStatus.PRAISE_EMOJI_REACTION_NOT_FOUND);
    }
}
