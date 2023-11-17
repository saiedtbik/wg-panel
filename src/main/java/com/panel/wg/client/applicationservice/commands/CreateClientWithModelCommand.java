package com.panel.wg.client.applicationservice.commands;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record CreateClientWithModelCommand(
        String clientId,
        String clientName,
        boolean enabled,
        String address,
        String publicKey,
        String preSharedKey,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime latestHandshakeAt,
        Long userId) {
}
