package com.panel.wg.common.domain.exceptions;

public class InvalidOperationException extends BaseException {
    public InvalidOperationException() {
        super(CommonError.SERVER_ERROR);
    }

    public InvalidOperationException(String message) {
        super(message, CommonError.SERVER_ERROR);
    }

    public InvalidOperationException(String message, Throwable cause) {
        super(message, cause, CommonError.SERVER_ERROR);
    }

    public InvalidOperationException(Throwable cause) {
        super(cause, CommonError.SERVER_ERROR);
    }
}
