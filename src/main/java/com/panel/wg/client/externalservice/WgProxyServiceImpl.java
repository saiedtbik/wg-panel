package com.panel.wg.client.externalservice;

import com.panel.wg.client.applicationservice.commands.CreateClientCommand;
import com.panel.wg.client.externalservice.model.ClientModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WgProxyServiceImpl implements WgProxyService {

    private final RestTemplate restTemplate;
    private static final String GET_CLIENT_PATH = "/api/wireguard/client";
    private static final String CREATE_CLIENT_PATH = "/api/wireguard/client";
    private static final String ENABLE_CLIENT_PATH = "/api/wireguard/client/:clientId/enable";
    private static final String DISABLE_CLIENT_PATH = "/api/wireguard/client/:clientId/disable";

    @Value("${app.wg.ip}")
    private  String host;

    @Value("${appl.wg.port}")
    private  String port;

    @Override
    public ClientModel createClient(CreateClientCommand command) {
        return null;
    }

    @Override
    public ClientModel getClient(String clientId) {
        String uri = generateURI(host, port, GET_CLIENT_PATH);
//        ResponseEntity<List<ClientModel>> response = restTemplate.postForEntity(uri, authInput, ClientModel.class);
        return null;
    }

    @Override
    public void enableClient(String clientId) {

    }

    @Override
    public void disableClient(String clientId) {

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
