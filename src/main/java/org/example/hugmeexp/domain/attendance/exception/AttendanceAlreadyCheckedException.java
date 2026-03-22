package org.example.hugmeexp.domain.attendance.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class AttendanceAlreadyCheckedException extends BaseException {
    public AttendanceAlreadyCheckedException() {
        super(ErrorStatus.ATTENDANCE_ALREADY_CHECKED);
    }
}
