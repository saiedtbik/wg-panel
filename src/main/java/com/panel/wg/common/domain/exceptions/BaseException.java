package com.panel.wg.common.domain.exceptions;

public abstract class BaseException extends RuntimeException {
    private final ApplicationError error;

    public BaseException(ApplicationError err) {
        super();
        this.error = err;

    }

    public BaseException(String message, ApplicationError err) {
        super(message);
        this.error = err;
    }

    public BaseException(String message, Throwable cause, ApplicationError err) {
        super(message, cause);
        this.error = err;
    }

    public BaseException(Throwable cause, ApplicationError err) {
        super(cause);
        this.error = err;
    }

    public ApplicationError getError() {
        return error;
    }
}
