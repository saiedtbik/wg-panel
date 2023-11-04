package com.panel.wg.client.externalservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panel.wg.client.externalservice.exception.WgException;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.stream.Collectors;

public class CoreRestErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError()
        );
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

    }


    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        if (hasError(response)) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getBody()))) {
                String httpBodyResponse = reader.lines()
                        .collect(Collectors.joining(""));

                ObjectMapper mapper = new ObjectMapper();
                RestTemplateError restTemplateError = mapper
                        .readValue(httpBodyResponse,
                                RestTemplateError.class);

                restTemplateError.setPath(url.getPath());

                response.getHeaders();
                throw new WgException(
                        restTemplateError.getPath(),
                        response.getStatusCode(),
                        restTemplateError.getStatus());
            }

        }
    }
}

@Data
class RestTemplateError {
    String path;
    String status;
    String message;
}
