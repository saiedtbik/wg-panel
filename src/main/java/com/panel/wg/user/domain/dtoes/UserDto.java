package com.panel.wg.user.domain.dtoes;

import com.panel.wg.client.domain.valueObjects.ClientStatus;
import lombok.Builder;

@Builder
public record UserDto(
        String fullName,
        String username,
        boolean enabled,
        String clientId,
        ClientStatus clientStatus) {
    public UserDto(String fullName, String username, boolean enabled, String clientId, ClientStatus clientStatus) {
        this.fullName = fullName;
        this.username = username;
        this.enabled = enabled;
        this.clientId = clientId;
        this.clientStatus = clientStatus;
    }

}
