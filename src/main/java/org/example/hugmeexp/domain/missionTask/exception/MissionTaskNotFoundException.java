package org.example.hugmeexp.domain.missionTask.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class MissionTaskNotFoundException extends BaseException {
    public MissionTaskNotFoundException() {
        super(ErrorStatus.MISSION_TASK_NOT_FOUND);
    }
}
