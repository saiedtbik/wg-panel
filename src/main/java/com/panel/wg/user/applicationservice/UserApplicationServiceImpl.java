package com.panel.wg.user.applicationservice;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.commnadHandlers.CreateClientHandler;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.user.applicationservice.commands.CreateCreditorUserCommand;
import com.panel.wg.user.applicationservice.commands.CreateUserCommand;
import com.panel.wg.user.applicationservice.commands.DisableUserCommand;
import com.panel.wg.user.applicationservice.mapper.UserDataMapper;
import com.panel.wg.user.domain.dtoes.UserDto;
import com.panel.wg.user.domain.entities.Role;
import com.panel.wg.user.domain.entities.User;
import com.panel.wg.user.domain.exceptions.UserError;
import com.panel.wg.user.repository.UserEntity;
import com.panel.wg.user.repository.UserRepository;
import com.panel.wg.user.utils.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;
    private final PasswordEncoder passwordEncoder;
    private final CreateClientHandler clientHandler;

    @Transactional
    public User findUserByUserName(String username) {
        return userRepository.findByApiKey(username)
                .map((userEntity -> userDataMapper.toUser(userEntity)))
                .orElseThrow(() -> new BusinessRuleViolationException(UserError.USER_NOT_FOUND));
    }

    public User createUser(CreateUserCommand command) {
        if (userExistByApiKey(command.apiKey())) {
            throw new BusinessRuleViolationException(UserError.USER_ALREADY_EXIST);
        }
        User user = User.builder()
                .apiKey(command.apiKey())
                .secretKey(passwordEncoder.encode(command.secretKey()))
                .fullName(command.fullName())
                .role(command.role())
                .enabled(true)
                .build();
        UserEntity userEntity = userRepository.save(userDataMapper.toUserEntity(user));

        clientHandler.apply(CreateClientCommand.builder()
                .clientName(user.getApiKey())
                .build());


        return User.builder()
                .id(userEntity.getId())
                .apiKey(userEntity.getApiKey())
                .secretKey(user.getSecretKey())
                .role(Role.valueOf(user.getRole().name()))
                .enabled(userEntity.isEnabled())
                .fullName(user.getFullName())
                .build();
    }

    public void disableUser(DisableUserCommand command) {
        UserEntity userEntity = userRepository.findByApiKey(command.userName())
                .orElseThrow(() -> new BusinessRuleViolationException(UserError.USER_NOT_FOUND));

        User user = userDataMapper.toUser(userEntity);
        user.setEnabled(false);
        userRepository.save(userDataMapper.toUserEntity(user));
    }

    @Override
    public boolean userExistByApiKey(String apiKey) {
        return userRepository.findByApiKey(apiKey).isPresent();
    }

    @Override
    public Optional<UserDto> createCreditorUser(CreateCreditorUserCommand command) {
        String apiKey = Principal.generateRandomAPIKey();
        String secretKey = Principal.generateRandomSecretKey();

        CreateUserCommand createUserCommand = CreateUserCommand.builder()
                .fullName(command.fullName())
                .apiKey(apiKey)
                .secretKey(secretKey)
                .role(Role.CLIENT_USER)
                .build();
        createUser(createUserCommand);

        UserDto userDto = UserDto.builder()
                .username(apiKey)
                .fullName(createUserCommand.fullName())
                .role(createUserCommand.role())
                .build();

        return Optional.ofNullable(userDto);
    }
}
