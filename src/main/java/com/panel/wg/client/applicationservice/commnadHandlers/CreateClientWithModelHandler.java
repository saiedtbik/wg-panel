package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.commands.CreateClientWithModelCommand;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.entities.Traffic;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import com.panel.wg.user.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class CreateClientWithModelHandler implements Function<CreateClientWithModelCommand, CreateClientDto> {
    private final ClientRepository clientRepository;

    @Override
    public CreateClientDto apply(CreateClientWithModelCommand createClientCommand) {

        Client newClient = Client.builder()
                .clientId(createClientCommand.clientId())
                .clientName(createClientCommand.clientName())
                .user(new User(createClientCommand.userId()))
                .publicKey(createClientCommand.publicKey())
                .address(createClientCommand.address())
                .updatedAt(createClientCommand.updatedAt())
                .createdAt(createClientCommand.createdAt())
                .status(createClientCommand.enabled() ? ClientStatus.ACTIVE : ClientStatus.DISABLED)
                .build();


        Traffic traffic = Traffic.builder()
                .capacity(100000000000L)
                .tempRx(0L)
                .tempTx(0L)
                .status(TrafficStatus.ACTIVE)
                .expirationDate(LocalDate.now().plusMonths(1))
                .createAt(LocalDateTime.now())
                .transferRx(createClientCommand.transferRx())
                .transferTx(createClientCommand.transferTx())
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
