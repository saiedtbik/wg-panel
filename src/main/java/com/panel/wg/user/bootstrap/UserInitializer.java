package com.panel.wg.user.bootstrap;

import com.panel.wg.user.applicationservice.UserApplicationService;
import com.panel.wg.user.applicationservice.commands.CreateAdminUserCommand;
import com.panel.wg.user.applicationservice.commands.CreateUserCommand;
import com.panel.wg.user.domain.entities.Role;
import com.panel.wg.user.domain.entities.User;
import com.panel.wg.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInitializer implements CommandLineRunner {
    @Value("${admin.password}")
    String adminPass;

    private final UserApplicationService userApplicationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userApplicationService.userExistByApiKey("admin")) {
            CreateAdminUserCommand command = CreateAdminUserCommand.builder()
                    .apiKey("admin")
                    .secretKey(adminPass)
                    .fullName("Admin")
                    .build();
            userApplicationService.createAdminUser(command);
        }else{
            User admin = userApplicationService.findUserByUserName("admin");
            admin.setSecretKey(passwordEncoder.encode(adminPass));
            userApplicationService.save(admin);
        }
    }
}
