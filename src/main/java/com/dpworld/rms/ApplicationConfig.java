package com.dpworld.rms;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Objects;

@Configuration
public class ApplicationConfig {
    final Environment environment;

    public ApplicationConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .setConnectTimeout(Duration.ofSeconds(Long.parseLong(Objects.requireNonNull(environment.getProperty("rates.surcharge.connect.timeout")))))
                .setReadTimeout(Duration.ofSeconds(Long.parseLong(Objects.requireNonNull(environment.getProperty("rates.surcharge.read.timeout")))))
                .build();
    }
}
