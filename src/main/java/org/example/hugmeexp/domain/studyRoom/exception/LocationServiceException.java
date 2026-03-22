package org.example.hugmeexp.domain.studyRoom.exception;

import org.example.hugmeexp.global.common.exception.BaseException;
import org.example.hugmeexp.global.common.exception.code.ErrorStatus;

public class LocationServiceException extends BaseException {
    public LocationServiceException(String message) {
        super(ErrorStatus.LOCATION_SERVICE_ERROR, message);
    }
}
