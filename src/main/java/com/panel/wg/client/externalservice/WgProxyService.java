package com.panel.wg.client.externalservice;

import com.panel.wg.client.externalservice.model.ClientModel;
import com.panel.wg.client.externalservice.model.CreateClientModel;

import java.util.List;
import java.util.Optional;

public interface WgProxyService {

    String auth();
    ClientModel createClient(CreateClientModel createClientModel);
    Optional<ClientModel> getClient(String clientId);
    ClientModel[] getAllClients();
    List<ClientModel> getAllActiveClients();
    void enableClient(String clientId);

    void disableClient(String clientId);

    byte[] getConfigFile(String clientId);

    byte[] getQRCodeFile(String clientId);

    void deleteClient(String clientId);
}
