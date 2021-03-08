package com.dpworld.rms.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SurchargeServiceImpl implements SurchargeService {
    private final RestTemplate restTemplate;
    @Value("${rates.surcharge.uri}")
    private String uri;
    private static final Logger logger = LoggerFactory.getLogger(SurchargeServiceImpl.class);

    public SurchargeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "defaultSurcharge")
    public Map<String,String> getSurcharge() {
        Map<String,String> surcharges = new HashMap<>();
        try {
            String surcharge = restTemplate.getForObject(uri, String.class);
            surcharges.put("surcharge", surcharge);
        }catch (RuntimeException exception) {
            logger.error("Error while fetching Surcharge. Returning defaults" + exception);
            surcharges = defaultSurcharge();
            }
        return surcharges;
    }

    public Map<String,String> defaultSurcharge() {
        logger.error("Returning defaults");
        Map<String,String> surcharges = new HashMap<>();
        surcharges.put("fuelSurcharge","$100");
        surcharges.put("terminalHandlingFee","$50");
        surcharges.put("equipmentHandlingFee","$25");
        return surcharges;
    }
}
