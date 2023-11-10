package com.panel.wg.user.domain.dtoes;

import com.panel.wg.user.domain.entities.Role;
import lombok.Builder;

@Builder
public record UserDto(
        String fullName,
        String username,
        Role role) {
}
