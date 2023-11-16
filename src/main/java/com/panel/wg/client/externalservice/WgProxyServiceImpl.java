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

    //
//    @Override
//    public InitiateMandateResponseModel initiateOneClickPaymentMandate(InitiateOneClickPaymentModel model) {
//        HttpHeaders header = generateBearerJWTAuthHeader();
//        HttpEntity<InitiateOneClickPaymentModel> httpEntity = new HttpEntity<>(model, header);
//        String uri = generateURI(host, port, "/api/v1/mandate/initiate/one-click-payment");
//        ResponseEntity<InitiateMandateResponseModel> response = restTemplate.postForEntity(uri, httpEntity, InitiateMandateResponseModel.class);
//        return response.getBody();
//    }
//
//    @Override
//    public InitiateMandateResponseModel initiateSubscriptionMandate(InitiateSubscriptionMandateModel model) {
//        HttpHeaders header = generateBearerJWTAuthHeader();
//        HttpEntity<InitiateSubscriptionMandateModel> httpEntity = new HttpEntity<>(model, header);
//        String uri = generateURI(host, port, "/api/v1/mandate/initiate/subscription");
//        ResponseEntity<InitiateMandateResponseModel> response = restTemplate.postForEntity(uri, httpEntity, InitiateMandateResponseModel.class);
//        return response.getBody();
//    }
//
//
//    @Override
//    public void disableMandate(DisableMandateModel model) {
//        HttpHeaders header = generateBearerJWTAuthHeader();
//        HttpEntity<DisableMandateModel> httpEntity = new HttpEntity<>(model, header);
//        String uri = generateURI(host, port, "/api/v1/mandate/disable");
//        restTemplate.postForEntity(uri, httpEntity, Void.class);
//    }
//
//    @Override
//    public void revokeMandate(RevokeMandateModel model) {
//        HttpHeaders header = generateBearerJWTAuthHeader();
//        HttpEntity<RevokeMandateModel> httpEntity = new HttpEntity<>(model, header);
//        String uri = generateURI(host, port, "/api/v1/mandate/revoke");
//        restTemplate.postForEntity(uri, httpEntity, Void.class);
//    }
//
//    private AuthResponseModel getToken() {
//        AuthRequestModel authInput = AuthRequestModel.builder()
//                .apiKey("jibit-gateway")
//                .secretKey("123456")
//                .build();
//
//        HttpEntity<AuthRequestModel> httpEntity = new HttpEntity<>(authInput);
//        String uri = generateURI(host, port, "/api/v1/auth/authenticate");
//        ResponseEntity<AuthResponseModel> response = restTemplate.postForEntity(uri, authInput, AuthResponseModel.class);
//        return response.getBody();
//    }
//
//    private HttpHeaders generateBearerJWTAuthHeader() {
//        AuthResponseModel authToken = getToken();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer ".concat(authToken.accessToken()));
//        return headers;
//    }

    private String generateURI(String host, String port, String url) {
        return "http://".concat(host).concat(":").concat(port).concat(url);
    }
}
