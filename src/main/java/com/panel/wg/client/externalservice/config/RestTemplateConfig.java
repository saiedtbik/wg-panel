package com.panel.wg.client.externalservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
        return new CustomRestTemplateCustomizer();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(customRestTemplateCustomizer());
    }

    @Bean
    public CoreRestErrorHandler coreRestErrorHandler() {
        return new CoreRestErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder()
                .errorHandler(coreRestErrorHandler())
                .build();
    }
}
