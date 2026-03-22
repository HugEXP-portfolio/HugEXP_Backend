package org.example.hugmeexp.domain.missionGroup.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class NotExistsUserMissionGroupException extends BaseException {
    public NotExistsUserMissionGroupException() {
        super(ErrorStatus.NOT_EXISTS_USER_MISSION_GROUP);
    }
}
