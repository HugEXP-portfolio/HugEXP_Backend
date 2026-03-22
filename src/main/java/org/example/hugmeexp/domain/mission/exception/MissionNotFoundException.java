package org.example.hugmeexp.domain.mission.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class MissionNotFoundException extends BaseException {
    public MissionNotFoundException() {
        super(ErrorStatus.MISSION_NOT_FOUND);
    }
}
