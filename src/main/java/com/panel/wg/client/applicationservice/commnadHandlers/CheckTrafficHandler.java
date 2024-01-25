package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CheckTrafficHandler {
    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;
    private final ResetClientWgTransferHandler resetClientWgTransferHandler;

    public void handle() {
        Map<String, Client> allActiveClients = clientRepository.findAllActiveClients();
        for (Client c : allActiveClients.values()) {
            try {
                if (!c.getCurrentTraffic().isPresent()) {
                    c.setActiveTraffic();
                    resetClientWgTransferHandler.handle(c.getClientId());
                    clientRepository.add(c);
                } else {
                    boolean isChanged = c.changeTrafficIfNeeded();
                    if (isChanged) {
                        resetClientWgTransferHandler.handle(c.getClientId());
                    }
                }

            } catch (BusinessRuleViolationException e) {
                wgProxyService.disableClient(c.getClientId());
                c.disableClient();
                clientRepository.add(c);
            }

        }
    }
}
