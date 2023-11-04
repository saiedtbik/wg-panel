package com.panel.wg.common.domain.exceptions;

public class InvalidInputException extends BaseException{
    public InvalidInputException(ApplicationError err) {
        super(err);
    }

    public InvalidInputException(String message, ApplicationError err) {
        super(message, err);
    }

    public InvalidInputException(String message, Throwable cause, ApplicationError err) {
        super(message, cause, err);
    }

    public InvalidInputException(Throwable cause, ApplicationError err) {
        super(cause, err);
    }
}
