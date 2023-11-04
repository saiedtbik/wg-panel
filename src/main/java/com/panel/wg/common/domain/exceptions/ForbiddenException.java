package com.panel.wg.common.domain.exceptions;

public class ForbiddenException extends BaseException {

    public ForbiddenException() {
        super(CommonError.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message, CommonError.SERVER_ERROR);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause, CommonError.SERVER_ERROR);
    }

    public ForbiddenException(Throwable cause) {
        super(cause, CommonError.SERVER_ERROR);
    }
}
