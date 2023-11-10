package com.panel.wg.user.bootstrap;

import com.panel.wg.user.applicationservice.UserApplicationService;
import com.panel.wg.user.applicationservice.commands.CreateUserCommand;
import com.panel.wg.user.domain.entities.Role;
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
