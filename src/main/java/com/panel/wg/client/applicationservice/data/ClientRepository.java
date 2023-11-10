package com.panel.wg.client.applicationservice.data;

import com.panel.wg.client.domain.entities.Client;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ClientRepository {
    Optional<Client> find(String clientId);
    void add(Client client);
    boolean exists(String clientId);

    List<Client> findAll();

    Map<String, Client> findAllActiveClients();
}
