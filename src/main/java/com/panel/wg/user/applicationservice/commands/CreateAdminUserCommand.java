package com.panel.wg.user.applicationservice.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateAdminUserCommand(
        @NotNull()
        @NotBlank()
        String apiKey,
        @NotNull()
        @NotBlank()
        String secretKey,
        @NotNull()
        @NotBlank()
        String fullName) {

}
