package com.panel.wg.client.applicationservice.commands;

import lombok.Builder;

@Builder
public record UpdateTrafficTransferCommand(
        String clientId) {
}
