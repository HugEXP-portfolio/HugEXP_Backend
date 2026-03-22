package org.example.hugmeexp.domain.bookmark.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class BookmarkUserNotFoundException extends BaseException {
    public BookmarkUserNotFoundException() {
        super(ErrorStatus.BOOKMARK_USER_NOT_FOUND);
    }
}
