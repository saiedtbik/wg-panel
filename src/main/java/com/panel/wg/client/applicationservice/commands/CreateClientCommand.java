package com.panel.wg.client.applicationservice.commands;

import com.panel.wg.client.domain.valueObjects.ClientStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateClientCommand(
        @NotNull
        @NotBlank
        String clientName,
        @NotNull
        Long userId,

        @NotNull
        ClientStatus clientStatus) {
}
