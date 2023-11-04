package com.panel.wg.client.externalservice;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.externalservice.model.ClientModel;

public interface WgProxyService {
    ClientModel createClient(CreateClientCommand command);
    ClientModel getClient(String clientId);
    void enableClient(String clientId);

    void disableClient(String clientId);
}
