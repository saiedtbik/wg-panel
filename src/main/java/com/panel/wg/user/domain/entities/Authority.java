package com.panel.wg.user.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Authority {
    AUTHENTICATE("authenticate"),

    /*---------------------------------- User Authorities --------------------------------------------------- */
    CREATE_USER("createUser"),
    QUERY_USER("queryUser"),
    DISABLE_USER("disableUser"),
    QUERY_ALL_USER("queryAllUser"),

    DISABLE_CLIENT("disableClient"),

    ENABLE_CLIENT("enableClient"),

    ;
    @Getter
    private final String permission;
}
