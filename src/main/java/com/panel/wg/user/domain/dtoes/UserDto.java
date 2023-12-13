package com.panel.wg.user.domain.dtoes;

import com.github.mfathi91.time.PersianDate;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto(
        String fullName,
        String username,
        boolean enabled,
        String clientId,
        ClientStatus clientStatus,
        LocalDateTime createDate) {
    public UserDto(String fullName, String username, boolean enabled, String clientId, ClientStatus clientStatus, LocalDateTime createDate) {
        this.fullName = fullName;
        this.username = username;
        this.enabled = enabled;
        this.clientId = clientId;
        this.clientStatus = clientStatus;
        this.createDate = createDate;
    }

    public String getJalaliCreateDate() {
        return PersianDate.fromGregorian(createDate.toLocalDate()).toString();
    }

}
