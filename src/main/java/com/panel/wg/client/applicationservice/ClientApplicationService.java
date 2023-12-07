package com.panel.wg.client.applicationservice;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.commands.CreateTrafficCommand;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;
import com.panel.wg.client.domain.dtoes.TrafficDto;

import java.util.List;

public interface ClientApplicationService {
    CreateClientDto createClient(CreateClientCommand command);
    void disableClient(String username);
    void enableClient(String username);
    void resetClientsWgTransfer(String username);
    void addTraffic(CreateTrafficCommand command);

    List<TrafficDto> getAllTraffics(String username);

    void resetAllClientsWgTransfer();

    void enableAllClients();

    void stop();
}
