package com.dpworld.rms.service;

import com.dpworld.rms.dto.RateAndSurcharge;
import com.dpworld.rms.model.Rate;
import com.dpworld.rms.repository.RatesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
// @ExtendWith attaches a runner with the test class to initialize the test data
public class RatesServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(RatesServiceImplTest.class);
    //@InjectMocks annotation is used to create and inject the mock object
    @InjectMocks
    private RatesServiceImpl ratesService;
    //@Mock annotation is used to create the mock object to be injected
    @Mock
    private RatesRepository ratesRepository;
    @Mock
    private SurchargeServiceImpl surchargeService;
    @Spy
    private RateAndSurcharge rateAndSurcharge;
    private List<Rate> rates;
    private Map<String, String> surcharges;

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
    }

    @Test
    void getRates() {
        when(ratesRepository.findAll()).thenReturn(rates);
        List<Rate> empList = (List<Rate>) ratesService.getRates();
        assertEquals(3, empList.size());
        verify(ratesRepository, times(1)).findAll();
    }

    @Test
    void findRateById() {
        when(ratesRepository.findByRateId(1L)).thenReturn(Optional.ofNullable(rates.get(1)));
        when(surchargeService.getSurcharge()).thenReturn(surcharges);
        RateAndSurcharge rate = ratesService.findRateById(1L);
        if(rate.getRate().isPresent()) {
            assertEquals(2L, rate.getRate().get().getRateId());
            assertEquals("description2", rate.getRate().get().getRateDescription());
            assertEquals("$100", surcharges.get("fuelSurcharge"));
        }
        verify(ratesRepository, times(1)).findByRateId(1L);
        verify(surchargeService, times(1)).getSurcharge();
    }

    @Test
    void addRate() {
        Rate rate = new Rate(1L,"description1",new Date(),new Date(),961);
        when(ratesRepository.save(rate)).thenReturn(rate);
        assertNotNull(ratesService.addRate(rate));
        assertEquals(961, rate.getAmount());
        verify(ratesRepository, times(1)).save(rate);
    }

    @Test
    void updateRateById() {
        Long id = 1L;
        Rate dbRate = new Rate(1L,"description1",new Date(),new Date(),961);
        Rate updateRate = new Rate(null,"description1",new Date(),new Date(),999);
        Optional<Rate> updated;
        when(ratesRepository.findByRateId(id)).thenReturn(Optional.of(dbRate));
        when(ratesRepository.save(dbRate)).thenReturn(updateRate);
        updated = ratesService.findRateById(id).getRate().map(rate -> {
            rate.setRateDescription(updateRate.getRateDescription());
            rate.setAmount(updateRate.getAmount());
            rate.setRateExpirationDate(updateRate.getRateExpirationDate());
            rate.setRateEffectiveDate(updateRate.getRateExpirationDate());
            rate.setRateId(id);
            return ratesRepository.save(rate);
        });
        assertTrue(updated.isPresent());
        assertEquals(999, updated.get().getAmount());
        verify(ratesRepository).save(any());
    }

    @Test
    void deleteRateById() {
        ratesRepository.deleteById(1L);
        verify(ratesRepository).deleteById(any());
    }
}