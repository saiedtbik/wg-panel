package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.client.externalservice.model.ClientModel;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class CreateClientHandler implements Function<CreateClientCommand, CreateClientDto> {
    ClientRepository clientRepository;
    WgProxyService wgProxyService;

    @Override
    public CreateClientDto apply(CreateClientCommand createClientCommand) {
        ClientModel clientModel = wgProxyService.createClient(createClientCommand);
        Client newClient = Client.builder()
                .clientId(clientModel.clientId())
                .clientName(clientModel.clientName())
                .publicKey(clientModel.publicKey())
                .address(clientModel.address())
                .latestHandshakeAt(clientModel.latestHandshakeAt())
                .updatedAt(clientModel.updatedAt())
                .createdAt(clientModel.createdAt())

                .status(ClientStatus.ACTIVE)
                .build();
        clientRepository.add(newClient);

        return CreateClientDto.builder()
                .clientId(newClient.getClientId())
                .clientName(newClient.getClientName())
                .status(newClient.getStatus())
                .build();
    }
}
