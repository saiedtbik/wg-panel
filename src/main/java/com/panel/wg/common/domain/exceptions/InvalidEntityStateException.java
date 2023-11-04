package com.panel.wg.common.domain.exceptions;

public class InvalidEntityStateException extends BaseException {
    public InvalidEntityStateException() {
        super(CommonError.SERVER_ERROR);
    }

    public InvalidEntityStateException(String message) {
        super(message, CommonError.SERVER_ERROR);
    }

    public InvalidEntityStateException(String message, Throwable cause) {
        super(message, cause, CommonError.SERVER_ERROR);
    }

    public InvalidEntityStateException(Throwable cause) {
        super(cause, CommonError.SERVER_ERROR);
    }
}
