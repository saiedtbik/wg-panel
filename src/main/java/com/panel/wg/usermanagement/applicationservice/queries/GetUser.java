package com.panel.wg.usermanagement.applicationservice.queries;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record GetUser(
        @NotNull
        @NotBlank
        String username) {
}
