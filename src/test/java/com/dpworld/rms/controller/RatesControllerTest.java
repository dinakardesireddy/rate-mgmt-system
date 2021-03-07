package com.dpworld.rms.controller;

import com.dpworld.rms.dto.RateAndSurcharge;
import com.dpworld.rms.model.Rate;
import com.dpworld.rms.service.RatesServiceImpl;
import com.google.common.collect.Iterables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatesControllerTest {
    @InjectMocks
    private RatesController ratesController;
    @Mock
    private RatesServiceImpl ratesService;
    private List<Rate> rates;
    private Map<String, String> surcharges;
    @Spy
    private RateAndSurcharge rateAndSurcharge;


    @BeforeEach
    void setUp() {
        rates = new ArrayList<>();
        rates.add(new Rate(1L,"description1",new Date(),new Date(),961));
        rates.add(new Rate(2L,"description2",new Date(),new Date(),962));
        rates.add(new Rate(3L,"description3",new Date(),new Date(),963));

        surcharges = new HashMap<String, String>()
        {{
            put("fuelSurcharge", "$100");
            put("terminalHandlingFee", "$50");
            put("equipmentHandlingFee", "$25");
        }};
        rateAndSurcharge.setSurcharge(surcharges);
        rateAndSurcharge.setRate(Optional.of(new Rate(1L, "description1", new Date(), new Date(), 961)));
    }

    @Test
    void getRates() {
        when(ratesService.getRates()).thenReturn(rates);
        Iterable<Rate> allRates = ratesController.getRates();
        assertNotNull(allRates);
        assertEquals(3, Iterables.size(allRates));
        verify(ratesService, times(1)).getRates();
    }

    @Test
    void getRateById() {
        when(ratesService.findRateById(1L)).thenReturn(rateAndSurcharge);
        RateAndSurcharge forId = ratesController.getRateById(1L);
        assertNotNull(forId);
        assertEquals("$100", forId.getSurcharge().get("fuelSurcharge"));
        verify(ratesService, times(1)).findRateById(1L);
    }

    @Test
    void addRate() {
        Rate rate = new Rate(1L,"description1",new Date(),new Date(),961);
        when(ratesService.addRate(rate)).thenReturn(rate);
        assertNotNull(ratesController.addRate(rate));
        verify(ratesService, times(1)).addRate(rate);
    }

    @Test
    void updateRateById() {
        Rate rate = new Rate(1L,"description1",new Date(),new Date(),961);
        when(ratesService.updateRateById(1L, rate)).thenReturn(rate);
        assertNotNull(ratesController.updateRateById(1L, rate));
        verify(ratesService, times(1)).updateRateById(1L, rate);
    }

    @Test
    void deleteRateById() {
        ratesService.deleteRateById(any());
        verify(ratesService).deleteRateById(any());
    }
}