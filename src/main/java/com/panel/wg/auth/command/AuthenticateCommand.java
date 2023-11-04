package com.panel.wg.auth.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AuthenticateCommand(
        @NotNull
        @NotBlank
        String username,

        @NotNull
        @NotBlank
        String password) {
}
