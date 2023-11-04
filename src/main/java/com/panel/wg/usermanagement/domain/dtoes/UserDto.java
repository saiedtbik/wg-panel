package com.panel.wg.usermanagement.domain.dtoes;

import com.panel.wg.usermanagement.domain.entities.Role;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDto(
        String fullName,
        String username,
        Role role) {
}
