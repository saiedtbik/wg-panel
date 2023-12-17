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
        LocalDateTime createDate,

        String mobileNum,
        String email
) {
    public UserDto(String fullName, String username, boolean enabled, String clientId, ClientStatus clientStatus, LocalDateTime createDate, String mobileNum, String email) {
        this.fullName = fullName;
        this.username = username;
        this.enabled = enabled;
        this.clientId = clientId;
        this.clientStatus = clientStatus;
        this.createDate = createDate;
        this.mobileNum = mobileNum;
        this.email = email;
    }

    public String getJalaliCreateDate() {
        return this.createDate == null ? null : PersianDate.fromGregorian(createDate.toLocalDate()).toString();
    }

}
