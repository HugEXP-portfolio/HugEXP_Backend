package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class InvalidReservationTimeException extends BaseException {
    public InvalidReservationTimeException() {
        super(ErrorStatus.INVALID_RESERVATION_TIME);
    }
}
