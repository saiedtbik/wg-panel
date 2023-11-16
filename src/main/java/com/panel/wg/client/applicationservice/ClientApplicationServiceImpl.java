package com.panel.wg.client.applicationservice;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.applicationservice.commands.DisableClientCommand;
import com.panel.wg.client.applicationservice.commands.EnableClientCommand;
import com.panel.wg.client.applicationservice.commnadHandlers.DisableClientHandler;
import com.panel.wg.client.applicationservice.commnadHandlers.EnableClientHandler;
import com.panel.wg.client.applicationservice.commnadHandlers.RefreshClientHandler;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.applicationservice.dtoes.CreateClientDto;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.exceptions.ClientError;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClientApplicationServiceImpl implements ClientApplicationService{
    private final ClientRepository clientRepository;
    private final DisableClientHandler disableClientHandler;
    private final EnableClientHandler enableClientHandler;
    private final RefreshClientHandler refreshClientHandler;

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


    @Override
    public void refreshClient(String username) {
        Client client = clientRepository.findClientByUsername(username)
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

        refreshClientHandler.handle(client.getClientId());
    }
}
