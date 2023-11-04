package com.panel.wg.client.applicationservice.dtoes;

import com.panel.wg.client.domain.valueObjects.ClientStatus;
import lombok.Builder;

@Builder
public record CreateClientDto(
        String clientName,
        String clientId,
        ClientStatus status) {
}
