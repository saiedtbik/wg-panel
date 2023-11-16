package com.panel.wg.client.applicationservice;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;

public interface ClientApplicationService {
    CreateClientDto createClient(CreateClientCommand command);
    void disableClient(String username);
    void enableClient(String username);
    void refreshClient(String username);
}
