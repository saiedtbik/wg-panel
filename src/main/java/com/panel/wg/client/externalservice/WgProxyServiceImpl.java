package com.panel.wg.client.externalservice;

import com.panel.wg.client.externalservice.model.ClientModel;
import com.panel.wg.client.externalservice.model.CreateClientModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

    @Value("${app.wg.pass}")
    private String pass;


    @Override
    public String auth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        Map<String, String> body = new HashedMap();
        body.put("password", pass);
        String uri = generateURI(host, port, "/api/session");
        System.out.println("*************************************");
        System.out.println("uri =" + uri);
        System.out.println("*************************************");

        ResponseEntity<String> result = restTemplate.postForEntity(uri, body, String.class);
        System.out.println("*************************************");
        System.out.println("result =" + result.getHeaders());
        System.out.println("*************************************");

        return result.getHeaders().get("Set-Cookie").get(0);
    }

    @Override
    public ClientModel createClient(CreateClientModel createClientModel) {
        String sessionId = auth();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);

        String uri = generateURI(host, port, CLIENT_PATH);
        HttpEntity<CreateClientModel> httpEntity = new HttpEntity<>(createClientModel, headers);
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

        String sessionId = auth();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Cookie", sessionId);

        HttpEntity  httpEntity = new HttpEntity<>(headers);

        String uri = generateURI(host, port, CLIENT_PATH);
        ResponseEntity<ClientModel[]> clients = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, ClientModel[].class);
        return clients.getBody();

    }


    @Override
    public void enableClient(String clientId) {
        String sessionId = auth();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        HttpEntity httpEntity = new HttpEntity<>(headers);


        StringBuilder path = new StringBuilder();
        path.append(CLIENT_PATH);
        path.append("/");
        path.append(clientId);
        path.append("/enable");


        String uri = generateURI(host, port, path.toString());
        restTemplate.postForEntity(uri, httpEntity, Object.class);
    }

    @Override
    public void disableClient(String clientId) {

        String sessionId = auth();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", sessionId);
        HttpEntity httpEntity = new HttpEntity<>(headers);

        StringBuilder path = new StringBuilder();
        path.append(CLIENT_PATH);
        path.append("/");
        path.append(clientId);
        path.append("/disable");

        String uri = generateURI(host, port, path.toString());
        restTemplate.postForEntity(uri, httpEntity, Object.class);
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
