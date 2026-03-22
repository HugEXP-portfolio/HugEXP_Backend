package org.example.hugmeexp.domain.user.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class UnsupportedImageExtensionException extends BaseException {
    public UnsupportedImageExtensionException(String ext) {
        super(ErrorStatus.UNSUPPORTED_IMAGE_EXTENSION, ext);
    }
}
