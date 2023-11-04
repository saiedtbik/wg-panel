package com.panel.wg.client.applicationservice.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateClientCommand(
        @NotNull
        @NotBlank
        String clientName) {
}
