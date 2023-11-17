package com.panel.wg.client.externalservice;

import com.panel.wg.client.externalservice.model.ClientModel;
import com.panel.wg.client.externalservice.model.CreateClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WgProxyServiceImpl implements WgProxyService {

    private final RestTemplate restTemplate;
    private static final String CLIENT_PATH = "/api/wireguard/client";

    @Value("${app.wg.ip}")
    private String host;

    @Value("${appl.wg.port}")
    private String port;

    @Override
    public ClientModel createClient(CreateClientModel createClientModel) {
        String uri = generateURI(host, port, CLIENT_PATH);
        HttpEntity<CreateClientModel> httpEntity = new HttpEntity<>(createClientModel);
        ResponseEntity<ClientModel> clients = restTemplate.postForEntity(uri, httpEntity, ClientModel.class);
        ClientModel clientModel = clients.getBody();
        ClientModel[] allClients = getAllClients();
        String id = Arrays.stream(allClients)
                .filter(c -> c.getPublicKey().equals(clientModel.getPublicKey()))
                .findFirst()
                .map(c -> c.getId())
                .get();
        clientModel.setId(id);
        return clientModel;
    }

    @Override
    public Optional<ClientModel> getClient(String clientId) {
        return Arrays.stream(getAllClients())
                .filter(c -> c.getId().equals(clientId))
                .findFirst();
    }

    public ClientModel[] getAllClients() {
        String uri = generateURI(host, port, CLIENT_PATH);
        ResponseEntity<ClientModel[]> clients = restTemplate.getForEntity(uri, ClientModel[].class);
        return clients.getBody();
    }


    @Override
    public void enableClient(String clientId) {
        StringBuilder path = new StringBuilder();
        path.append(CLIENT_PATH);
        path.append("/");
        path.append(clientId);
        path.append("/enable");

        String uri = generateURI(host, port, path.toString());
        restTemplate.postForEntity(uri, null, Object.class);
    }

    @Override
    public void disableClient(String clientId) {
        StringBuilder path = new StringBuilder();
        path.append(CLIENT_PATH);
        path.append("/");
        path.append(clientId);
        path.append("/disable");

        String uri = generateURI(host, port, path.toString());
        restTemplate.postForEntity(uri, null, Object.class);
    }

    @Override
    public List<ClientModel> getAllActiveClients() {
        return Arrays.stream(getAllClients())
                .filter(c -> c.isEnabled())
                .toList();
    }



    private String generateURI(String host, String port, String url) {
        return "http://".concat(host).concat(":").concat(port).concat(url);
    }
}
