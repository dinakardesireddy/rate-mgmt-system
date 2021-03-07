package com.dpworld.rms.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurchargeServiceImplTest {
    @InjectMocks
    private SurchargeServiceImpl surchargeService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private Environment environment;

    @Test
    void getSurcharge() {
        when(restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("rates.surcharge.uri")), String.class))
                .thenReturn("{\n" +
                        "  \"fuel-surcharge\": \"$40\",\n" +
                        "  \"terminal handling fee\": \"$30\",\n" +
                        "  \"equipment handling fee\": \"$60\"\n" +
                        "}");
        assertNotNull(surchargeService.getSurcharge());
        verify(restTemplate, times(1))
                .getForObject(Objects.requireNonNull(environment.getProperty("rates.surcharge.uri")), String.class);
    }
}