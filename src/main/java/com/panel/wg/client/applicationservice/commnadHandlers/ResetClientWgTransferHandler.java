package com.panel.wg.client.applicationservice.commnadHandlers;


import com.panel.wg.client.externalservice.WgProxyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ResetClientWgTransferHandler {

    private final WgProxyService wgProxyService;

    public void handle(String clientId) {
        wgProxyService.disableClient(clientId);
        wgProxyService.enableClient(clientId);
    }
}
