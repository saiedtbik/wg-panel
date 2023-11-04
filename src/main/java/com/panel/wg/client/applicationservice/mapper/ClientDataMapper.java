package com.panel.wg.client.applicationservice.mapper;

import com.panel.wg.client.dataaccess.entities.ClientEntity;
import com.panel.wg.client.domain.entities.Client;

import java.util.Objects;

public class ClientDataMapper {

    public static Client toClient(ClientEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return Client.builder()
                .clientName(entity.getClientName())
                .clientId(entity.getClientId())
                .address(entity.getAddress())
                .publicKey(entity.getPublicKey())
                .latestHandshakeAt(entity.getLatestHandshakeAt())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .trafficList(entity.getTrafficList()
                        .stream()
                        .map(trafficEntity -> TrafficMapper.toTraffic(trafficEntity))
                        .toList())
                .currentTraffic(TrafficMapper.toTraffic(entity.getCurrentTraffic()))
                .build();
    }

    public static ClientEntity toEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setClientId(entity.getClientId());
        entity.setClientName(client.getClientName());
        entity.setAddress(client.getAddress());
        entity.setStatus(client.getStatus());
        entity.setTrafficList(client.getTrafficList()
                .stream()
                .map(traffic -> TrafficMapper.toEntity(traffic))
                .toList());
        entity.setCurrentTraffic(TrafficMapper.toEntity(client.getCurrentTraffic()));
        entity.setLatestHandshakeAt(client.getLatestHandshakeAt());
        entity.setStatus(client.getStatus());
        entity.setCreatedAt(client.getCreatedAt());
        entity.setUpdatedAt(client.getUpdatedAt());
        return entity;
    }
}
