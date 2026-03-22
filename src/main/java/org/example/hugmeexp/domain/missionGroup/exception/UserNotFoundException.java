package org.example.hugmeexp.domain.missionGroup.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(ErrorStatus.MISSION_GROUP_USER_NOT_FOUND);
    }
}
