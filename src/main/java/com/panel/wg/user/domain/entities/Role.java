package com.panel.wg.user.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractDomainDataRegion;

import java.util.Set;
import static com.panel.wg.user.domain.entities.Authority.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of
                    (
                            CREATE_USER,
                            DISABLE_USER,
                            QUERY_USER,
                            QUERY_ALL_USER,
                            DISABLE_CLIENT,
                            ENABLE_CLIENT,
                            ENABLE_ALL_CLIENTS,
                            ADD_TRAFFIC,
                            VIEW_TRAFFIC,
                            RESET_CLIENT_WG_TRAFFIC,
                            REST_ALL_CLIENTS_WG_TRAFFIC,
                            GENERATE_USERS_FROM_WG_CLIENTS,
                            STOP

                    )),
    CLIENT_USER(
            Set.of
                    (

                    )),

    ;


    @Getter
    private final Set<Authority> authorities;

}
