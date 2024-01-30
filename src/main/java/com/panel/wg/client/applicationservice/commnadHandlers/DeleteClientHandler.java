package com.panel.wg.client.applicationservice.commnadHandlers;
import com.panel.wg.client.applicationservice.commands.DeleteClientCommand;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.domain.entities.Client;
import com.panel.wg.client.domain.exceptions.ClientError;
import com.panel.wg.client.externalservice.WgProxyService;
import com.panel.wg.client.externalservice.model.ClientModel;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class DeleteClientHandler implements Consumer<DeleteClientCommand> {


    private final ClientRepository clientRepository;
    private final WgProxyService wgProxyService;
    private final UserRepository userRepository;

    @Override
    public void accept(DeleteClientCommand deleteClientCommand) {
        Client client = clientRepository.find(deleteClientCommand.clientId())
                .orElseThrow(() -> new BusinessRuleViolationException(ClientError.CLIENT_NOT_EXIST));

        Optional<ClientModel> wgClient = wgProxyService.getClient(client.getClientId());
        if (wgClient.isPresent()) {
            throw new BusinessRuleViolationException(ClientError.CLIENT_WG_EXIST);
        }

        userRepository.deleteById(client.getUser().getId());
        clientRepository.delete(client.getClientId());
    }
}
