package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class CommentEmojiReactionNotFoundException extends BaseException {
    public CommentEmojiReactionNotFoundException() {
        super(ErrorStatus.COMMENT_EMOJI_REACTION_NOT_FOUND);
    }
}
