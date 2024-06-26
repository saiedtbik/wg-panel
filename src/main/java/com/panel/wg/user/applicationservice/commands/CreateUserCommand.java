package com.panel.wg.user.applicationservice.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.panel.wg.user.domain.entities.Role;
import lombok.Builder;

@Builder
public record CreateUserCommand(
//        @NotNull()
//        @NotBlank()
        String firstName,

//        @NotNull()
//        @NotBlank()
        String lastName,

        @NotNull()
        @NotBlank()
        String username,
//        @NotNull()
//        @NotBlank()
        String password,

//        @NotNull()
//        @NotBlank()
        String repassword,

        String email,
        String mobileNumber,

        String telegramId
        ) {
}