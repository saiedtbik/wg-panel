package com.panel.wg.client.applicationservice.commnadHandlers;


import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.client.externalservice.model.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UpdateActiveClientsTransferTrafficHandler {

    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;

    public void handle() {
        List<ClientModel> clientModels = wgProxyService.getAllActiveClients();
        Map<String, Client> clients = clientRepository.findAllActiveClients();

        for (ClientModel clientModel : clientModels) {
            Client client = clients.get(clientModel.getId());
            client.updateCurrentTrafficTransfer(clientModel.getTransferRx(), clientModel.getTransferTx());
            clientRepository.add(client);
        }
    }


}
