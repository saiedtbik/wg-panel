package com.panel.wg.user.applicationservice.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ChangePasswordCommand(
        @NotNull()
        @NotBlank
        String username,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        @NotBlank
        String repeatPassword) {
}
