package com.panel.wg.client.applicationservice.mapper;

import com.panel.wg.client.dataaccess.entities.ClientEntity;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.user.applicationservice.mapper.UserDataMapper;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;


public class ClientDataMapper {

    public static Client toClient(ClientEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return Client.builder()
                .clientName(entity.getClientName())
                .clientId(entity.getClientId())
                .user(UserDataMapper.toUser(entity.getUserEntity()))
                .address(entity.getAddress())
                .publicKey(entity.getPublicKey())
                .latestHandshakeAt(entity.getLatestHandshakeAt())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .trafficList(entity.getTrafficList() == null ? null : entity.getTrafficList()
                        .stream()
                        .map(trafficEntity -> TrafficMapper.toTraffic(trafficEntity))
                        .collect(Collectors.toList()))
                .build();
    }

    public  static ClientEntity toEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setClientId(client.getClientId());
        entity.setClientName(client.getClientName());
        entity.setUserEntity(UserDataMapper.toUserEntity(client.getUser()));
        entity.setAddress(client.getAddress());
        entity.setStatus(client.getStatus());
        entity.setTrafficList(client.getTrafficList() == null ? new ArrayList<>() : client.getTrafficList()
                .stream()
                .map(traffic -> TrafficMapper.toEntity(traffic))
                .toList());
        entity.setPublicKey(client.getPublicKey());
        entity.setLatestHandshakeAt(client.getLatestHandshakeAt());
        entity.setStatus(client.getStatus());
        entity.setCreatedAt(client.getCreatedAt());
        entity.setUpdatedAt(client.getUpdatedAt());
        return entity;
    }
}
