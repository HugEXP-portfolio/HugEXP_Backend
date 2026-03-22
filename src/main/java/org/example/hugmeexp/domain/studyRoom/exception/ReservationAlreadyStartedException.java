package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class ReservationAlreadyStartedException extends BaseException {
    public ReservationAlreadyStartedException() {
        super(ErrorStatus.RESERVATION_ALREADY_STARTED);
    }
}
