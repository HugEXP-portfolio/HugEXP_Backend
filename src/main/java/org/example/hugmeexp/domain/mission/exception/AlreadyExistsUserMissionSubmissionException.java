package org.example.hugmeexp.domain.mission.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class AlreadyExistsUserMissionSubmissionException extends BaseException {
    public AlreadyExistsUserMissionSubmissionException() {
        super(ErrorStatus.ALREADY_EXISTS_USER_MISSION_SUBMISSION);
    }
}
