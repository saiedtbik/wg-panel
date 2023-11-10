package com.panel.wg.user.domain.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Set;
import static com.panel.wg.user.domain.entities.Authority.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of
                    (
                            CREATE_USER,
                            DISABLE_USER,
                            QUERY_USERS

                    )),
    CLIENT_USER(
            Set.of
                    (

                    )),

    ;


    @Getter
    private final Set<Authority> authorities;

}
