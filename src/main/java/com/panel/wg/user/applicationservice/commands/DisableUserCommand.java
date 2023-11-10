package com.panel.wg.user.applicationservice.commands;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DisableUserCommand(
        @NotNull
        String userName) {
}
