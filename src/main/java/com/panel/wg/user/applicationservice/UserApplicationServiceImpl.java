package com.panel.wg.user.applicationservice;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.commands.CreateClientWithModelCommand;
import com.panel.wg.client.applicationservice.commnadHandlers.CreateClientHandler;
import com.panel.wg.client.applicationservice.commnadHandlers.CreateClientWithModelHandler;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.client.externalservice.model.ClientModel;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.common.domain.tools.utilities.StringUtility;
import com.panel.wg.user.applicationservice.commands.CreateAdminUserCommand;
import com.panel.wg.user.applicationservice.commands.CreateUserCommand;
import com.panel.wg.user.applicationservice.commands.DisableUserCommand;
import com.panel.wg.user.applicationservice.mapper.UserDataMapper;
import com.panel.wg.user.domain.dtoes.UserDto;
import com.panel.wg.user.domain.entities.Role;
import com.panel.wg.user.domain.entities.User;
import com.panel.wg.user.domain.exceptions.UserError;
import com.panel.wg.user.repository.UserEntity;
import com.panel.wg.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CreateClientHandler createClientHandler;
    private final WgProxyService wgProxyService;
    private final CreateClientWithModelHandler createClientWithModelHandler;


    @Transactional
    public User findUserByUserName(String username) {
        return userRepository.findByApiKey(username)
                .map((userEntity -> UserDataMapper.toUser(userEntity)))
                .orElseThrow(() -> new BusinessRuleViolationException(UserError.USER_NOT_FOUND));
    }

    @Transactional
    public UserDto createUser(CreateUserCommand command) {
        if (userExistByApiKey(command.username())) {
            throw new BusinessRuleViolationException(UserError.USER_ALREADY_EXIST);
        }
        User user = User.builder()
                .apiKey(command.username())
                .secretKey(passwordEncoder.encode(command.password()))
                .fullName(command.fullName())
                .role(Role.CLIENT_USER)
                .createOn(LocalDateTime.now())
                .enabled(true)
                .build();
        UserEntity userEntity = userRepository.save(UserDataMapper.toUserEntity(user));

        CreateClientDto clientDto = createClientHandler.apply(CreateClientCommand.builder()
                .clientName(user.getFullName())
                .userId(userEntity.getId())
                .clientStatus(ClientStatus.DISABLED)
                .build());


        return UserDto.builder()
                .fullName(user.getFullName())
                .username(userEntity.getApiKey())
                .enabled(userEntity.isEnabled())
                .clientId(clientDto.clientId())
                .clientStatus(clientDto.status())
                .build();
    }

    @Transactional
    public void disableUser(DisableUserCommand command) {
        UserEntity userEntity = userRepository.findByApiKey(command.userName())
                .orElseThrow(() -> new BusinessRuleViolationException(UserError.USER_NOT_FOUND));

        User user = UserDataMapper.toUser(userEntity);
        user.setEnabled(false);
        userRepository.save(UserDataMapper.toUserEntity(user));
    }

    @Override
    public boolean userExistByApiKey(String apiKey) {
        return userRepository.findByApiKey(apiKey).isPresent();
    }

    @Transactional
    @Override
    public Optional<UserDto> createAdminUser(CreateAdminUserCommand command) {
        if (userExistByApiKey(command.apiKey())) {
            throw new BusinessRuleViolationException(UserError.USER_ALREADY_EXIST);
        }
        User user = User.builder()
                .apiKey(command.apiKey())
                .secretKey(passwordEncoder.encode(command.secretKey()))
                .fullName(command.fullName())
                .createOn(LocalDateTime.now())
                .role(Role.ADMIN)
                .enabled(true)
                .build();
        userRepository.save(UserDataMapper.toUserEntity(user));

        UserDto userDto = UserDto.builder()
                .username(user.getApiKey())
                .fullName(user.getFullName())
                .build();

        return Optional.ofNullable(userDto);
    }

    @Transactional
    @Override
    public void creatAllUserFromWgClients() {
        ClientModel[] allClients = wgProxyService.getAllClients();
        for (ClientModel clientModel : allClients) {
            if (userExistByClientId(clientModel.getId())) {
                continue;
            }

            User user = User.builder()
                    .apiKey(StringUtility.random(7))
                    .secretKey(passwordEncoder.encode("123456"))
                    .fullName(clientModel.getName())
                    .role(Role.CLIENT_USER)
                    .createOn(LocalDateTime.now())
                    .enabled(true)
                    .build();
            UserEntity userEntity = userRepository.save(UserDataMapper.toUserEntity(user));


            createClientWithModelHandler.apply(CreateClientWithModelCommand.builder()
                    .clientName(clientModel.getName())
                    .createdAt(clientModel.getCreatedAt())
                    .address(clientModel.getAddress())
                    .clientId(clientModel.getId())
                    .publicKey(clientModel.getPublicKey())
                    .preSharedKey(clientModel.getPreSharedKey())
                    .latestHandshakeAt(clientModel.getLatestHandshakeAt())
                    .userId(userEntity.getId())
                    .updatedAt(clientModel.getUpdatedAt())
                    .enabled(false)
                    .build());
        }

    }

    private boolean userExistByClientId(String clientId) {
      return userRepository.existsByClientId(clientId).isPresent();
    }
}