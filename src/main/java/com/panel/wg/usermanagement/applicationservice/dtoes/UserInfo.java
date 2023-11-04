package com.panel.wg.usermanagement.applicationservice.dtoes;

import com.panel.wg.usermanagement.domain.entities.Authority;
import com.panel.wg.usermanagement.domain.entities.Role;
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
