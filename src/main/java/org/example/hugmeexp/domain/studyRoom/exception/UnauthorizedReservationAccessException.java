package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UnauthorizedReservationAccessException extends BaseException {
    public UnauthorizedReservationAccessException() {
        super(ErrorStatus.UNAUTHORIZED_RESERVATION_ACCESS);
    }
}
