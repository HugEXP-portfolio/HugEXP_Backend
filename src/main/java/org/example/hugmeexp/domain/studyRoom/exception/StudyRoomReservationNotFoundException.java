package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class StudyRoomReservationNotFoundException extends BaseException {
    public StudyRoomReservationNotFoundException() {
        super(ErrorStatus.STUDY_ROOM_RESERVATION_NOT_FOUND);
    }
}
