package com.dpworld.rms.repository;

import com.dpworld.rms.model.Rate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class RatesRepositoryTest {

    @Autowired
    private RatesRepository ratesRepository;

    @Test
    void findByRateId() {
        Rate rate = new Rate(1L,"description1",new Date(),new Date(),961);
        ratesRepository.save(rate);
        Optional<Rate> rateFromDb = ratesRepository.findByRateId(1L);
        assertTrue(rateFromDb.isPresent());
        assertEquals(961, rateFromDb.get().getAmount());
    }
}