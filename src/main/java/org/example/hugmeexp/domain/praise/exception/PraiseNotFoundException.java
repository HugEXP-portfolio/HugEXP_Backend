package org.example.hugmeexp.domain.praise.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class PraiseNotFoundException extends BaseException {
    public PraiseNotFoundException() {
        super(ErrorStatus.PRAISE_NOT_FOUND);
    }
}
