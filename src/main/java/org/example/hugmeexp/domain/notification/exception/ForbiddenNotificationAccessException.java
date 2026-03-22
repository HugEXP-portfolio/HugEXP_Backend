package org.example.hugmeexp.domain.notification.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class ForbiddenNotificationAccessException extends BaseException {
    public ForbiddenNotificationAccessException() {
        super(ErrorStatus.FORBIDDEN_NOTIFICATION_ACCESS);
    }
}
