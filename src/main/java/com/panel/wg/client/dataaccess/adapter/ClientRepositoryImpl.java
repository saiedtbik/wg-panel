package com.panel.wg.client.dataaccess.adapter;

import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.applicationservice.mapper.ClientDataMapper;
import com.panel.wg.client.dataaccess.repositories.ClientJpaRepository;
import com.panel.wg.client.domain.entities.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
