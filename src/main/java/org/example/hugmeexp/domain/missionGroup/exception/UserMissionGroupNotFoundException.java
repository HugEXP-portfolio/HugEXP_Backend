package org.example.hugmeexp.domain.missionGroup.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UserMissionGroupNotFoundException extends BaseException {
    public UserMissionGroupNotFoundException() {
        super(ErrorStatus.USER_MISSION_GROUP_NOT_FOUND);
    }
}
