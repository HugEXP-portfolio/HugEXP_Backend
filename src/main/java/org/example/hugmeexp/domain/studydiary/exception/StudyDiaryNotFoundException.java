package org.example.hugmeexp.domain.studydiary.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class StudyDiaryNotFoundException extends BaseException {
    public StudyDiaryNotFoundException() {
        super(ErrorStatus.STUDY_DIARY_NOT_FOUND);
    }
}
