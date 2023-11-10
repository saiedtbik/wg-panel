package com.panel.wg.user.applicationservice.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateCreditorUserCommand(
        @NotNull()
        @NotBlank()
        String fullName
) {

}
