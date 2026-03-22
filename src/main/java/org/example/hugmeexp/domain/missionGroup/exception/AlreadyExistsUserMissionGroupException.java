package org.example.hugmeexp.domain.missionGroup.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class AlreadyExistsUserMissionGroupException extends BaseException {
    public AlreadyExistsUserMissionGroupException() {
        super(ErrorStatus.ALREADY_EXISTS_USER_MISSION_GROUP);
    }
}
