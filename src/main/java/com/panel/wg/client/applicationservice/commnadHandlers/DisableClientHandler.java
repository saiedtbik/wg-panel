package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.commands.DisableClientCommand;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.exceptions.ClientError;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class DisableClientHandler implements Consumer<DisableClientCommand> {

    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;

    @Transactional
    @Override
    public void accept(DisableClientCommand disableClientCommand) {
            Client client = clientRepository.find(disableClientCommand.clientId())
                    .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

        wgProxyService.disableClient(disableClientCommand.clientId());
        client.disableClient();
        clientRepository.add(client);

    }
}
