package com.panel.wg.client.applicationservice.commnadHandlers;

import com.github.mfathi91.time.PersianDate;
import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.entities.Traffic;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.client.externalservice.model.ClientModel;
import com.panel.wg.client.externalservice.model.CreateClientModel;
import com.panel.wg.user.applicationservice.mapper.UserDataMapper;
import com.panel.wg.user.domain.entities.User;
import com.panel.wg.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class CreateClientHandler implements Function<CreateClientCommand, CreateClientDto> {
    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;

    @Override
    public CreateClientDto apply(CreateClientCommand createClientCommand) {
        ClientModel clientModel = wgProxyService.createClient(new CreateClientModel(createClientCommand.clientName()));

        if (createClientCommand.clientStatus() == ClientStatus.DISABLED) {
            wgProxyService.disableClient(clientModel.getId());
        }

        Client newClient = Client.builder()
                .clientId(clientModel.getId())
                .clientName(clientModel.getName())
                .user(new User(createClientCommand.userId()))
                .publicKey(clientModel.getPublicKey())
                .address(clientModel.getAddress())
                .updatedAt(clientModel.getUpdatedAt())
                .createdAt(clientModel.getCreatedAt())
                .status(createClientCommand.clientStatus())
                .build();


        Traffic traffic = Traffic.builder()
                .capacity(100000000000L)
                .tempRx(0L)
                .tempTx(0L)
                .status(TrafficStatus.ACTIVE)
                .expirationDate(LocalDate.now().plusMonths(1))
                .createAt(LocalDateTime.now())
                .build();

        newClient.addTraffic(traffic);
        clientRepository.add(newClient);

        return CreateClientDto.builder()
                .clientId(newClient.getClientId())
                .clientName(newClient.getClientName())
                .status(newClient.getStatus())
                .build();
    }


}
