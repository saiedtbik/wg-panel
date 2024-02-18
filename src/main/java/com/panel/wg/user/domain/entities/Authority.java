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
    ENABLE_ALL_CLIENTS("enableClients"),
    ADD_TRAFFIC("addTraffic"),

    VIEW_TRAFFIC("viewTraffic"),

    RESET_CLIENT_WG_TRAFFIC("rest_client_wg_traffic"),
    REST_ALL_CLIENTS_WG_TRAFFIC("reset_client_wg_traffic"),
    GENERATE_USERS_FROM_WG_CLIENTS("generate_users_from_wg_clients"),

    STOP("stop"),

    ADD_CONFIG("/api/v1/add-endpoint/*"),
    DELETE_CLIENT("/api/v1/user/*"),
    DELETE_TRAFFIC("/api/v1/traffic/*"),
    VIEW_CONFIG("/api/v1/endpoint"),
    DOWNLOAD_CONFIG("/api/v1/client/*/configs"),
    QR("/api/v1/client/*/qr")

    ;
    @Getter
    private final String permission;
}
