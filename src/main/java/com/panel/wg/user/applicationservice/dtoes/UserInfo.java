package com.panel.wg.user.applicationservice.dtoes;

import com.panel.wg.user.domain.entities.Authority;
import com.panel.wg.user.domain.entities.Role;
import lombok.Builder;
import java.util.Set;
import java.util.UUID;

@Builder
public record UserInfo(
        UUID id,
        String fullName,
        String username,
        String password,
        Role roles,
        Set<Authority> authorities,
        boolean enabled) {

}
