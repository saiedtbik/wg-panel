package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.commands.EnableClientCommand;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.exceptions.ClientError;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class EnableClientHandler implements Consumer<EnableClientCommand> {

    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;

    @Override
    public void accept(EnableClientCommand enableClientCommand) {
        Client client = clientRepository.find(enableClientCommand.clientId())
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

        wgProxyService.enableClient(client.getClientId());
        client.enableClient();

        clientRepository.add(client);
    }
}
