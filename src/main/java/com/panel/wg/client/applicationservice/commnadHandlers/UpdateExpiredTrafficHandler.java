package com.panel.wg.client.applicationservice.commnadHandlers;

import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateExpiredTrafficHandler {

    private final ClientRepository clientRepository;

    public void handle() {
        List<Client> clientList = clientRepository.findAll();
        for (Client c : clientList) {
            c.setExpiredOutDatedTraffic();
            clientRepository.add(c);
        }
    }
}
