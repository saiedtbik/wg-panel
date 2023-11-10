package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CheckTrafficHandler {
    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;
    private final RefreshClientHandler refreshClientHandler;

    public void handle() {
        List<Client> clientList = clientRepository.findAll();
        for (Client c : clientList) {
            try {
                c.changeTrafficIfNeeded();
                refreshClientHandler.handle(c.getClientId());

            } catch (BusinessRuleViolationException e) {
                wgProxyService.disableClient(c.getClientId());
                c.disableClient();
            }
            clientRepository.add(c);
        }
    }
}
