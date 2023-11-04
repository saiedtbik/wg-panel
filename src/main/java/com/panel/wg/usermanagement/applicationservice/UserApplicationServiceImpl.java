package com.panel.wg.usermanagement.applicationservice;

import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.usermanagement.applicationservice.commands.CreateCreditorUserCommand;
import com.panel.wg.usermanagement.applicationservice.commands.CreateUserCommand;
import com.panel.wg.usermanagement.applicationservice.commands.DisableUserCommand;
import com.panel.wg.usermanagement.applicationservice.mapper.UserDataMapper;
import com.panel.wg.usermanagement.domain.dtoes.UserDto;
import com.panel.wg.usermanagement.domain.entities.Role;
import com.panel.wg.usermanagement.domain.entities.User;
import com.panel.wg.usermanagement.domain.exceptions.UserError;
import com.panel.wg.usermanagement.repository.UserEntity;
import com.panel.wg.usermanagement.repository.UserRepository;
import com.panel.wg.usermanagement.utils.Principal;
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
