package org.example.hugmeexp.domain.studydiary.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UnauthorizedAccessException extends BaseException {
    public UnauthorizedAccessException() {
        super(ErrorStatus.STUDY_DIARY_UNAUTHORIZED_ACCESS);
    }
}
