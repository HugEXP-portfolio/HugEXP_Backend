package org.example.hugmeexp.domain.recruitment.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class DuplicateRecruitmentBookmarkException extends BaseException {
    public DuplicateRecruitmentBookmarkException() {
        super(ErrorStatus.DUPLICATE_RECRUITMENT_BOOKMARK);
    }
}
