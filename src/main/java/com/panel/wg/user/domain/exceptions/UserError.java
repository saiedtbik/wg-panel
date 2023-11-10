package com.panel.wg.user.domain.exceptions;


import com.panel.wg.common.domain.exceptions.ApplicationError;

public enum UserError implements ApplicationError {


    //----------------------------------------------- Input Validation Error Code -------------------------------------------

    /*---------------------------------------------- Business Rule Validation Error ------------------------------*/

    USER_NOT_FOUND("user.not_found"),
    USER_ALREADY_EXIST("user.already_exist")

    ;
    private String key;

    UserError(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}