package com.panel.wg.client.applicationservice.commnadHandlers;


import com.panel.wg.client.applicationservice.commands.UpdateTrafficTransferCommand;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.exceptions.ClientError;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.client.externalservice.model.ClientModel;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class UpdateTrafficTransferHandler implements Consumer<UpdateTrafficTransferCommand> {

    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;

    @Override
    public void accept(UpdateTrafficTransferCommand updateTrafficTransferCommand) {
        ClientModel clientModel = wgProxyService.getClient(updateTrafficTransferCommand.clientId());
        Client client = clientRepository.find(clientModel.clientId())
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

        client.updateCurrentTrafficTransfer(clientModel.transferRx(), clientModel.transferTx());
        clientRepository.add(client);
    }
}
