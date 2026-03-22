package org.example.hugmeexp.domain.user.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class ProfileImageNotFoundException extends BaseException {
    public ProfileImageNotFoundException() {
        super(ErrorStatus.PROFILE_IMAGE_NOT_FOUND);
    }
}
