package com.panel.wg.client.applicationservice;

import com.github.mfathi91.time.PersianDate;
import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.commands.CreateTrafficCommand;
import com.panel.wg.client.applicationservice.commands.DisableClientCommand;
import com.panel.wg.client.applicationservice.commands.EnableClientCommand;
import com.panel.wg.client.applicationservice.commnadHandlers.DisableClientHandler;
import com.panel.wg.client.applicationservice.commnadHandlers.EnableClientHandler;
import com.panel.wg.client.applicationservice.commnadHandlers.ResetClientWgTransferHandler;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;
import com.panel.wg.client.applicationservice.mapper.TrafficMapper;
import com.panel.wg.client.dataaccess.entities.TrafficEntity;
import com.panel.wg.client.dataaccess.repositories.TrafficJpaRepository;
import com.panel.wg.client.domain.dtoes.TrafficDto;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.entities.Traffic;
import com.panel.wg.client.domain.exceptions.ClientError;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import com.panel.wg.client.domain.valueObjects.TrafficStatus;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.common.domain.tools.validators.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientApplicationServiceImpl implements ClientApplicationService {
    private final ClientRepository clientRepository;
    private final DisableClientHandler disableClientHandler;
    private final EnableClientHandler enableClientHandler;
    private final ResetClientWgTransferHandler resetClientWgTransferHandler;
    private final TrafficJpaRepository trafficJpaRepository;
    private final WgProxyService wgProxyService;


    @Override
    public CreateClientDto createClient(CreateClientCommand command) {
        return null;
    }

    @Transactional
    @Override
    public void disableClient(String clientId) {

        disableClientHandler.accept(new DisableClientCommand(clientId));

    }

    @Transactional
    @Override
    public void enableClient(String clientId) {
        enableClientHandler.accept(new EnableClientCommand(clientId));

    }

    @Transactional
    @Override
    public void enableAllClients() {
        // ClientModel[] allClients = wgProxyService.getAllClients();
        List<Client> clients = clientRepository.findAll();

        for (Client c : clients) {
            enableClientHandler.accept(new EnableClientCommand(c.getClientId()));
        }
    }

    @Override
    public void resetClientsWgTransfer(String clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

        resetClientWgTransferHandler.handle(client.getClientId());
    }


    @Override
    public void resetAllClientsWgTransfer() {
        //ClientModel[] allClients = wgProxyService.getAllClients();
        List<Client> clients = clientRepository.findAll();
        for (Client c : clients) {
            if (c.getStatus() == ClientStatus.ACTIVE)
                resetClientWgTransferHandler.handle(c.getClientId());
        }
    }

    @Transactional
    @Override
    public void addTraffic(CreateTrafficCommand command) {
        PersianDate persianDate = PersianDate.parse(command.expirationDate(), Validator.DATE_TIME_FORMATTER);
        if (command.id() == null) {
            Client client = clientRepository.findById(command.username())
                    .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

            Traffic traffic = Traffic.builder()
                    .status(TrafficStatus.CREATED)
                    .capacity(command.capacity() != null ? command.capacity() * 1000000000 : 100000000000l)
                    .tempCapacity(command.capacity() != null ? command.capacity() * 1000000000 : 100000000000l)
                    .expirationDate(persianDate.toGregorian())
                    .createAt(LocalDateTime.now())
                    .build();

            client.addTraffic(traffic);
            clientRepository.add(client);
        } else {
            TrafficEntity traffic = trafficJpaRepository.findById(command.id()).get();
            traffic.setCapacity(command.capacity() != null ? command.capacity() * 1000000000 : 100000000000l);
            traffic.setTempCapacity(command.capacity() != null ? command.capacity() * 1000000000 : 100000000000l);
            traffic.setExpirationDate(persianDate.toGregorian());
            trafficJpaRepository.save(traffic);
        }
    }

    @Transactional
    @Override
    public List<TrafficDto> getAllTraffics(String clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));
        return client.getTrafficList()
                .stream()
                .map(t -> TrafficMapper.toDto(t))
                .sorted(Comparator.comparing(t -> t.createAt()))
                .toList();
    }

    @Override
    public void stop() {
        List<TrafficEntity> trafficEntitiesByStatus = trafficJpaRepository.findTrafficEntitiesByStatus(TrafficStatus.ACTIVE);
        for (TrafficEntity traffic : trafficEntitiesByStatus) {
            traffic.setTempCapacity(traffic.getCapacity() - traffic.getTransferTx());
            trafficJpaRepository.save(traffic);
        }
    }

    @Override
    public byte[] getConfigFile(String clientId) {
        return wgProxyService.getConfigFile(clientId);
    }

    @Override
    public byte[] getQRCodeFile(String clientId) {
        return wgProxyService.getQRCodeFile(clientId);
    }
}
