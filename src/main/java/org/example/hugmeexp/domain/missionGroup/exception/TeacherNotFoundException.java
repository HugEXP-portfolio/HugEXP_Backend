package org.example.hugmeexp.domain.missionGroup.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class TeacherNotFoundException extends BaseException {
    public TeacherNotFoundException() {
        super(ErrorStatus.TEACHER_NOT_FOUND);
    }
}
