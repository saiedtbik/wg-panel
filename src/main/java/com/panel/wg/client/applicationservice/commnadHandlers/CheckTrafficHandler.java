package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CheckTrafficHandler {
    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;
    private final ResetClientWgTransferHandler resetClientWgTransferHandler;

    public void handle() {
        List<Client> clientList = clientRepository.findAll();
        for (Client c : clientList) {
            try {
                if (Objects.isNull(c.getCurrentTraffic())) {
                    c.setCurrentTraffic();
                }
                c.changeTrafficIfNeeded();
                resetClientWgTransferHandler.handle(c.getClientId());

            } catch (BusinessRuleViolationException e) {
                wgProxyService.disableClient(c.getClientId());
                c.disableClient();
            }
            clientRepository.add(c);
        }
    }
}
