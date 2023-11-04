package com.panel.wg.client.externalservice.model;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ClientModel(
        String clientId,
        String clientName,
        boolean enabled,
        String address,
        String publicKey,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime latestHandshakeAt,
        Long transferRx,
        Long transferTx) {
}
