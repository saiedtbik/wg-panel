package com.panel.wg.usermanagement.applicationservice;

import com.panel.wg.usermanagement.applicationservice.commands.CreateCreditorUserCommand;
import com.panel.wg.usermanagement.applicationservice.commands.CreateUserCommand;
import com.panel.wg.usermanagement.applicationservice.commands.DisableUserCommand;
import com.panel.wg.usermanagement.domain.dtoes.UserDto;
import com.panel.wg.usermanagement.domain.entities.User;
import java.util.Optional;

public interface UserApplicationService {
    boolean userExistByApiKey(String apiKey);

    User findUserByUserName(String username);

    User createUser(CreateUserCommand command);

    Optional<UserDto> createCreditorUser(CreateCreditorUserCommand command);

    void disableUser(DisableUserCommand command);
}
