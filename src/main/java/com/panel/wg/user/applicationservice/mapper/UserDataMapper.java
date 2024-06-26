package com.panel.wg.user.applicationservice.mapper;

import com.panel.wg.common.domain.tools.utilities.StringUtility;
import com.panel.wg.user.domain.dtoes.UserDto;
import com.panel.wg.user.domain.entities.Role;
import com.panel.wg.user.domain.entities.User;
import com.panel.wg.user.repository.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

public class UserDataMapper {
    public static User toUser(UserEntity entity) {
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
                .email(entity.getEmail())
                .mobileNum(entity.getMobileNum())
                .build();
        return user;
    }


    public static UserEntity toUserEntity(User user) {
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
        entity.setEmail(user.getEmail());
        entity.setMobileNum(user.getMobileNum());
        entity.setTelegramId(user.getTelegramId());
        return entity;
    }

    public static UserDto toUserDto(User user) {
        if(Objects.isNull(user)) {
            return null;
        }

        return UserDto.builder()
                .fullName(Objects.isNull(user.getFullName()) ? StringUtility.EMPTY : user.getFullName())
                .username(user.getApiKey())
                .build();
    }
}
