package com.panel.wg.client.applicationservice.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateTrafficCommand(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        Long capacity,
        @NotNull
        @NotBlank
        String expirationDate) {
}
