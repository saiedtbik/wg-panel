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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientApplicationServiceImpl implements ClientApplicationService {
    private final ClientRepository clientRepository;
    private final DisableClientHandler disableClientHandler;
    private final EnableClientHandler enableClientHandler;
    private final ResetClientWgTransferHandler resetClientWgTransferHandler;
    private final WgProxyService wgProxyService;

    @Override
    public CreateClientDto createClient(CreateClientCommand command) {
        return null;
    }

    @Transactional
    @Override
    public void disableClient(String username) {
        Client client = clientRepository.findClientByUsername(username)
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

        disableClientHandler.accept(new DisableClientCommand(client.getClientId()));

    }

    @Transactional
    @Override
    public void enableClient(String username) {
        Client client = clientRepository.findClientByUsername(username)
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));
        enableClientHandler.accept(new EnableClientCommand(client.getClientId()));

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
    public void resetClientsWgTransfer(String username) {
        Client client = clientRepository.findClientByUsername(username)
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

        Client client = clientRepository.findClientByUsername(command.username())
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

        PersianDate persianDate = PersianDate.parse(command.expirationDate(), Validator.DATE_TIME_FORMATTER);
        Traffic traffic = Traffic.builder()
                .status(TrafficStatus.CREATED)
                .capacity(command.capacity())
                .expirationDate(persianDate.toGregorian())
                .createAt(LocalDateTime.now())
                .build();

        client.addTraffic(traffic);
        clientRepository.add(client);
    }

    @Transactional
    @Override
    public List<TrafficDto> getAllTraffics(String username) {
        Client client = clientRepository.findClientByUsername(username)
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));
        return client.getTrafficList()
                .stream()
                .map(t -> TrafficMapper.toDto(t))
                .sorted(Comparator.comparing(t -> t.createAt()))
                .toList();
    }
}
