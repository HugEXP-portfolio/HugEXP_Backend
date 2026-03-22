package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class StudyHallNotFoundException extends BaseException {
    public StudyHallNotFoundException(Long studyHallId) {
        super(ErrorStatus.STUDY_HALL_NOT_FOUND, studyHallId);
    }
}
