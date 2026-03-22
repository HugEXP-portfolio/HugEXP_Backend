package org.example.hugmeexp.domain.studydiary.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class LikeNotFoundException extends BaseException {
    public LikeNotFoundException() {
        super(ErrorStatus.STUDY_DIARY_LIKE_NOT_FOUND);
    }
}
