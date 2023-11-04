package com.panel.wg.usermanagement.bootstrap;

import com.panel.wg.usermanagement.applicationservice.UserApplicationService;
import com.panel.wg.usermanagement.applicationservice.commands.CreateUserCommand;
import com.panel.wg.usermanagement.domain.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInitializer implements CommandLineRunner {
    @Value("${user.jibit.panenl.password}")
    String jibitPanelPass;

    private final UserApplicationService userApplicationService;

    @Override
    public void run(String... args) throws Exception {
        if (!userApplicationService.userExistByApiKey("admin")) {
            CreateUserCommand command = CreateUserCommand.builder()
                    .apiKey("admin")
                    .secretKey(jibitPanelPass)
                    .fullName("Admin")
                    .role(Role.ADMIN)
                    .build();
            userApplicationService.createUser(command);
        }
    }
}
