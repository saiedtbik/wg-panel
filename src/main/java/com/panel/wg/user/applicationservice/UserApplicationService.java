package com.panel.wg.user.applicationservice;

import com.panel.wg.user.applicationservice.commands.CreateAdminUserCommand;
import com.panel.wg.user.applicationservice.commands.CreateUserCommand;
import com.panel.wg.user.applicationservice.commands.DisableUserCommand;
import com.panel.wg.user.domain.dtoes.UserDto;
import com.panel.wg.user.domain.entities.User;
import java.util.Optional;

public interface UserApplicationService {
    boolean userExistByApiKey(String apiKey);

    User findUserByUserName(String username);

    UserDto createUser(CreateUserCommand command);

    Optional<UserDto> createAdminUser(CreateAdminUserCommand command);

    void disableUser(DisableUserCommand command);

    void creatAllUserFromWgClients();

    void save(User admin);
}
