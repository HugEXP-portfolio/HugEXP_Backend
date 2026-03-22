package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class StudyRoomNotFoundException extends BaseException {
    public StudyRoomNotFoundException(Long roomId) {
        super(ErrorStatus.STUDY_ROOM_NOT_FOUND, roomId);
    }
}
