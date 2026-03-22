package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class ReservationConflictException extends BaseException {
    public ReservationConflictException() {
        super(ErrorStatus.RESERVATION_CONFLICT);
    }
}
