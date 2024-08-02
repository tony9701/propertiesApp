package com.topHomes.propertiesApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean("messageRestClient")
    public RestClient messageRestClient(MessageApiConfig messageApiConfig) {
        return RestClient
                .builder()
                .baseUrl(messageApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
