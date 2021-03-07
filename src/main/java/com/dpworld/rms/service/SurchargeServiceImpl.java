package com.dpworld.rms.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SurchargeServiceImpl implements SurchargeService {
    private final Environment environment;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(SurchargeServiceImpl.class);

    public SurchargeServiceImpl(Environment environment, RestTemplate restTemplate) {
        this.environment = environment;
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "defaultSurcharge")
    public Map<String,String> getSurcharge() {
        Map<String,String> surcharges = new HashMap<>();
        try {
            String surcharge = restTemplate.getForObject(
                    Objects.requireNonNull(environment.getProperty("rates.surcharge.uri")), String.class);
            surcharges.put("surcharge", surcharge);
        }catch (RuntimeException exception) {
            logger.error("Error while fetching Surcharge. Returning defaults" + exception);
            surcharges = defaultSurcharge();
            }
        return surcharges;
    }

    public Map<String,String> defaultSurcharge() {
        Map<String,String> surcharges = new HashMap<>();
        surcharges.put("fuelSurcharge","$100");
        surcharges.put("terminalHandlingFee","$50");
        surcharges.put("equipmentHandlingFee","$25");
        return surcharges;
    }
}
