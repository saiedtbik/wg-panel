package com.panel.wg.user.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Authority {
    AUTHENTICATE("authenticate"),

    /*---------------------------------- User Authorities --------------------------------------------------- */
    CREATE_USER("createUser"),
    QUERY_USERS("queryUsers"),
    DISABLE_USER("disableUser"),

    ;
    @Getter
    private final String permission;
}
