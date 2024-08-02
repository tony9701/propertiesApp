package com.topHomes.propertiesApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class RestConfig {

    @Bean()
    public RestClient offersRestClient(MessageApiConfig messageApiConfig) {
        return RestClient
                .builder()
                .baseUrl(messageApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
