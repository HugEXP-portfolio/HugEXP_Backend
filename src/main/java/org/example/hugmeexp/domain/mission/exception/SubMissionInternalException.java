package org.example.hugmeexp.domain.mission.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class SubMissionInternalException extends BaseException {
    public SubMissionInternalException(String message) {
        super(message, ErrorStatus.SUB_MISSION_INTERNAL);
    }

    public SubMissionInternalException() {
        super(ErrorStatus.SUB_MISSION_INTERNAL);
    }
}
