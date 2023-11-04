package com.panel.wg.usermanagement.applicationservice.commands;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.util.UUID;

@Builder
public record DisableUserCommand(
        @NotNull
        String userName) {
}
