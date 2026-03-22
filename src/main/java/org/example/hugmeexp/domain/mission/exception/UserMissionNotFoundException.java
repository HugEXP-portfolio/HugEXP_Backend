package org.example.hugmeexp.domain.mission.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UserMissionNotFoundException extends BaseException {
    public UserMissionNotFoundException() {
        super(ErrorStatus.USER_MISSION_NOT_FOUND);
    }
}
