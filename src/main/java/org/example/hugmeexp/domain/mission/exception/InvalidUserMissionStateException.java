package org.example.hugmeexp.domain.mission.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class InvalidUserMissionStateException extends BaseException {
    public InvalidUserMissionStateException() {
        super(ErrorStatus.INVALID_USER_MISSION_STATE);
    }
}
