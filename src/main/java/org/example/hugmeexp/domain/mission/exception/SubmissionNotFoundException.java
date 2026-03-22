package org.example.hugmeexp.domain.mission.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class SubmissionNotFoundException extends BaseException {
    public SubmissionNotFoundException(String message) {
        super(message, ErrorStatus.SUBMISSION_NOT_FOUND);
    }

    public SubmissionNotFoundException() {
        super(ErrorStatus.SUBMISSION_NOT_FOUND);
    }
}
