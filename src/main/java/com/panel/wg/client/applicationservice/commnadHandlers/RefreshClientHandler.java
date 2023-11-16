package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.exceptions.ClientError;
import com.panel.wg.client.domain.valueObjects.ClientStatus;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshClientHandler {

    private final WgProxyService wgProxyService;
    private final ClientRepository clientRepository;

    public void handle(String clientId) {
        Client client = clientRepository.find(clientId).orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));
        if (client.getStatus() == ClientStatus.DISABLED) {
            throw new BusinessRuleViolationException(ClientError.CLIENT_NOT_ACTIVE);
        }
        wgProxyService.disableClient(clientId);
        wgProxyService.enableClient(clientId);
    }
}
