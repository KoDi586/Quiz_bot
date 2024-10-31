package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    // @TODO добавить путь localhost в yaml файл
    @Value("${name.service.localhost}")
    private String localhostDataBase;

    @Bean
    public RestClient restClientDataBase(){
        return RestClient.create(localhostDataBase);
    }

}
