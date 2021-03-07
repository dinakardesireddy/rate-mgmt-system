package com.dpworld.rms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class RmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmsApplication.class, args);
    }

}
