package org.example.hugmeexp.domain.recruitment.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class RecruitmentNotFoundException extends BaseException {
    public RecruitmentNotFoundException() {
        super(ErrorStatus.RECRUITMENT_NOT_FOUND);
    }
}
