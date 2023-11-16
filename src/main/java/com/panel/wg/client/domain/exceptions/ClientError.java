package com.panel.wg.client.domain.exceptions;

import com.panel.wg.common.domain.exceptions.ApplicationError;

public enum ClientError implements ApplicationError {



    /*---------------------------------------------- Business Rule Validation Error ------------------------------*/

    CLIENT_NO_TRAFFIC("client.no_traffic"),
    CLIENT_NOT_EXIST("client.not.exist"),
    CLIENT_NOT_ACTIVE("client.not.active"),

    ;

    private String key;

    ClientError(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}