package edu.t1.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {
    @Value("${app.user-day-limit-service-url}")
    private String userDayLimitServiceUrl;
    @Bean
    RestClient usersDayLimitRestClient(){
        return RestClient.builder()
                .baseUrl(userDayLimitServiceUrl)
                .build();
    }
}
