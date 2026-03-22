package org.example.hugmeexp.domain.notification.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class NotificationNotFoundException extends BaseException {
    public NotificationNotFoundException() {
        super(ErrorStatus.NOTIFICATION_NOT_FOUND);
    }
}
