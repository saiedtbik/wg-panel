package com.panel.wg.client.externalservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientModel{
        String id;
        String name;
        boolean enabled;
        String address;
        String publicKey;
        String preSharedKey;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
        LocalDateTime latestHandshakeAt;
        Long transferRx;
        Long transferTx;
}
