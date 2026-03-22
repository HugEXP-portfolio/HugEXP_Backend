package org.example.hugmeexp.domain.missionGroup.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class MissionGroupNotFoundException extends BaseException {
    public MissionGroupNotFoundException() {
        super(ErrorStatus.MISSION_GROUP_NOT_FOUND);
    }
}
