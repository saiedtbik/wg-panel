package com.panel.wg.common.domain.exceptions;

public class BusinessRuleViolationException extends BaseException{
    public BusinessRuleViolationException(ApplicationError err) {
        super(err);
    }

    public BusinessRuleViolationException(String message, ApplicationError err) {
        super(message, err);
    }

    public BusinessRuleViolationException(String message, Throwable cause, ApplicationError err) {
        super(message, cause, err);
    }

    public BusinessRuleViolationException(Throwable cause, ApplicationError err) {
        super(cause, err);
    }
}
