package org.example.hugmeexp.domain.mission.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class SubmissionFileUploadException extends BaseException {
    public SubmissionFileUploadException(String message) {
        super(message, ErrorStatus.SUBMISSION_FILE_UPLOAD);
    }

    public SubmissionFileUploadException() {
        super(ErrorStatus.SUBMISSION_FILE_UPLOAD);
    }
}
