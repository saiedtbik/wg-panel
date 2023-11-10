package com.panel.wg.user.applicationservice.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.panel.wg.user.domain.entities.Role;
import lombok.Builder;

@Builder
public record CreateUserCommand(
        @NotNull()
        @NotBlank()
        String apiKey,
        @NotNull()
        @NotBlank()
        String secretKey,
        @NotNull()
        @NotBlank()
        String fullName,
        @NotNull()
        Role role) {
}