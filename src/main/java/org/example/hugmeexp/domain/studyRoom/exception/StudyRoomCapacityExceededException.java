package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class StudyRoomCapacityExceededException extends BaseException {
    public StudyRoomCapacityExceededException() {
        super(ErrorStatus.STUDY_ROOM_CAPACITY_EXCEEDED);
    }
}
