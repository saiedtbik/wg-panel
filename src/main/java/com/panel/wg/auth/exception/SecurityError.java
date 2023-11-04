package com.panel.wg.auth.exception;


import com.panel.wg.common.domain.exceptions.ApplicationError;

public enum SecurityError implements ApplicationError {

    INVALID_CREDENTIALS("credentials.invalid"),
    INVALID_TOKEN("token.invalid")
    ;

    private String key;

    SecurityError(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
