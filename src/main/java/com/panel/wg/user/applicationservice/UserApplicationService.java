package com.panel.wg.user.applicationservice;

import com.panel.wg.user.applicationservice.commands.CreateCreditorUserCommand;
import com.panel.wg.user.applicationservice.commands.CreateUserCommand;
import com.panel.wg.user.applicationservice.commands.DisableUserCommand;
import com.panel.wg.user.domain.dtoes.UserDto;
import com.panel.wg.user.domain.entities.User;
import java.util.Optional;

public interface UserApplicationService {
    boolean userExistByApiKey(String apiKey);

    User findUserByUserName(String username);

    User createUser(CreateUserCommand command);

    Optional<UserDto> createCreditorUser(CreateCreditorUserCommand command);

    void disableUser(DisableUserCommand command);
}
