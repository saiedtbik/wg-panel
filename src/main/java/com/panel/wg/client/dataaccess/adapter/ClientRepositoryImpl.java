package com.panel.wg.client.dataaccess.adapter;

import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.applicationservice.mapper.ClientDataMapper;
import com.panel.wg.client.dataaccess.entities.ClientEntity;
import com.panel.wg.client.dataaccess.repositories.ClientJpaRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;

    @Override
    public Optional<Client> find(String clientId) {
        return clientJpaRepository.findById(clientId)
                .map(entity -> ClientDataMapper.toClient(entity));
    }

    @Override
    public void add(Client client) {
        clientJpaRepository.save(ClientDataMapper.toEntity(client));
    }

    @Override
    public boolean exists(String clientId) {
        return clientJpaRepository.existsById(clientId);
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll()
                .stream().map(clientEntity -> ClientDataMapper.toClient(clientEntity))
                .toList();
    }

    @Override
    public Map<String, Client> findAllActiveClients() {
        return findAll().stream()
                .filter(c -> c.getStatus().equals(ClientStatus.ACTIVE))
                .collect(Collectors.toMap(c -> c.getClientId(), c -> c, (oldVal, newVal) -> newVal));
    }

    @Override
    public Optional<Client> findClientByUsername(String username) {
        Optional<ClientEntity> clientUserInfo = clientJpaRepository.findClientUserInfo(username);
        if (!clientUserInfo.isPresent()) {
            return Optional.empty();
        }
        return Optional.ofNullable(ClientDataMapper.toClient(clientUserInfo.get()));
    }
}
