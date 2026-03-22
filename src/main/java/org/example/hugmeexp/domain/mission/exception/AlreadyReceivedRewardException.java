package org.example.hugmeexp.domain.mission.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class AlreadyReceivedRewardException extends BaseException {
    public AlreadyReceivedRewardException() {
        super(ErrorStatus.ALREADY_RECEIVED_REWARD);
    }
}
