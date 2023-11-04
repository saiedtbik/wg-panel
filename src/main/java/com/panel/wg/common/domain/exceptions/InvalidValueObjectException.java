package com.panel.wg.common.domain.exceptions;

public class InvalidValueObjectException extends BaseException {
    public InvalidValueObjectException() {
        super(CommonError.SERVER_ERROR);
    }

    public InvalidValueObjectException(String message) {
        super(message, CommonError.SERVER_ERROR);
    }

    public InvalidValueObjectException(String message, Throwable cause) {
        super(message, cause, CommonError.SERVER_ERROR);
    }

    public InvalidValueObjectException(Throwable cause) {
        super(cause, CommonError.SERVER_ERROR);
    }
}
