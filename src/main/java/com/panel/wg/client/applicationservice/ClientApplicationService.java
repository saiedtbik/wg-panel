package com.panel.wg.client.applicationservice;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.commands.CreateTrafficCommand;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;
import com.panel.wg.client.domain.dtoes.TrafficDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientApplicationService {
    CreateClientDto createClient(CreateClientCommand command);
    void disableClient(String clientId);
    void enableClient(String clientId);
    void deleteClient(String clientId);
    void resetClientsWgTransfer(String clientId);
    void addTraffic(CreateTrafficCommand command);

    List<TrafficDto> getAllTraffics(String clientId);

    void resetAllClientsWgTransfer();

    void enableAllClients();

    void stop();

    byte[] getConfigFile(String clientId);

    byte[] getQRCodeFile(String clientId);
}
