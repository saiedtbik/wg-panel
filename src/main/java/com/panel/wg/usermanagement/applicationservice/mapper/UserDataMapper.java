package com.panel.wg.usermanagement.applicationservice.mapper;

import com.panel.wg.common.domain.tools.utilities.StringUtility;
import com.panel.wg.usermanagement.domain.dtoes.UserDto;
import com.panel.wg.usermanagement.domain.entities.Role;
import com.panel.wg.usermanagement.domain.entities.User;
import com.panel.wg.usermanagement.repository.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDataMapper {
    public User toUser(UserEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        User user = User.builder()
                .id(entity.getId())
                .apiKey(entity.getApiKey())
                .secretKey(entity.getSecretKey())
                .role(Role.valueOf(entity.getRole().name()))
                .createOn(entity.getCreateOn())
                .enabled(entity.isEnabled())
                .build();
        return user;
    }


    public UserEntity toUserEntity(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setFullName(user.getFullName());
        entity.setApiKey(user.getApiKey());
        entity.setSecretKey(user.getSecretKey());
        entity.setRole(user.getRole());
        entity.setEnabled(user.isEnabled());
        entity.setCreateOn(user.getCreateOn());
        return entity;
    }

    public static UserDto toUserDto(User user) {
        if(Objects.isNull(user)) {
            return null;
        }

        return UserDto.builder()
                .fullName(Objects.isNull(user.getFullName()) ? StringUtility.EMPTY : user.getFullName())
                .username(user.getApiKey())
                .role(user.getRole())
                .build();
    }
}
