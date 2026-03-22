package org.example.hugmeexp.domain.attendance.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class AttendanceUserNotFoundException extends BaseException {
    public AttendanceUserNotFoundException() {
        super(ErrorStatus.ATTENDANCE_USER_NOT_FOUND);
    }
}
