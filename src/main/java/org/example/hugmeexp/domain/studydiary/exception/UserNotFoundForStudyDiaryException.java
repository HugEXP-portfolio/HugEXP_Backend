package org.example.hugmeexp.domain.studydiary.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UserNotFoundForStudyDiaryException extends BaseException {
    public UserNotFoundForStudyDiaryException() {
        super(ErrorStatus.STUDY_DIARY_USER_NOT_FOUND);
    }
}
