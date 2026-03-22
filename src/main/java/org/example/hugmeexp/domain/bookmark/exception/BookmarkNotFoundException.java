package org.example.hugmeexp.domain.bookmark.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class BookmarkNotFoundException extends BaseException {
    public BookmarkNotFoundException() {
        super(ErrorStatus.BOOKMARK_NOT_FOUND);
    }
}
