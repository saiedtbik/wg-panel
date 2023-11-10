package com.panel.wg.client.externalservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateClientModel(
        @NotNull
        @NotBlank
        String clientName) {
}
