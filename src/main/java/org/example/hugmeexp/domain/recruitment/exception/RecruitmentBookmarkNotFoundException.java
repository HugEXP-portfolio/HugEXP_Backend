package org.example.hugmeexp.domain.recruitment.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class RecruitmentBookmarkNotFoundException extends BaseException {
    public RecruitmentBookmarkNotFoundException() {
        super(ErrorStatus.RECRUITMENT_BOOKMARK_NOT_FOUND);
    }
}
