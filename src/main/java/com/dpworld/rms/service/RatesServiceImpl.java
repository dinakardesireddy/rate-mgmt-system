package com.dpworld.rms.service;

import com.dpworld.rms.dto.RateAndSurcharge;
import com.dpworld.rms.exception.InternalServerException;
import com.dpworld.rms.exception.ResourceNotFoundException;
import com.dpworld.rms.model.Rate;
import com.dpworld.rms.repository.RatesRepository;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:rmserrors.properties")
public class RatesServiceImpl implements RatesService {
    private final RatesRepository ratesRepository;
    private final Environment environment;
    private final SurchargeServiceImpl surchargeServiceImpl;
    private final RateAndSurcharge rateAndSurcharge;

    public RatesServiceImpl(RatesRepository ratesRepository, Environment environment,
                            SurchargeServiceImpl surchargeServiceImpl, RateAndSurcharge rateAndSurcharge) {
        this.ratesRepository = ratesRepository;
        this.environment = environment;
        this.surchargeServiceImpl = surchargeServiceImpl;
        this.rateAndSurcharge = rateAndSurcharge;
    }
    @Override
    public RateAndSurcharge findRateById(Long rateId) {
        rateAndSurcharge.setRate(ratesRepository.findByRateId(rateId));
        rateAndSurcharge.setSurcharge(surchargeServiceImpl.getSurcharge());
        if (!rateAndSurcharge.getRate().isPresent()) {
            throw new ResourceNotFoundException(environment.getProperty("not.found"));
        }
        return rateAndSurcharge;
    }
    @Override
    public Iterable<Rate> getRates() {
        try {
            return ratesRepository.findAll();
        } catch (RuntimeException exception) {
            throw new InternalServerException(environment.getProperty("internal.error"));
        }
    }
    @Override
    public Rate addRate(Rate rate) {
        try {
            return ratesRepository.save(rate);
        } catch (RuntimeException exception) {
            throw new InternalServerException(environment.getProperty("internal.error"));
        }
    }
    @Override
    public Rate updateRateById(Long id, Rate newRate) {
        try {
            //Efficient when the number of fields to update are small. For updating a large number of fields implement the DTO mapping implementations.
            return ratesRepository.findById(id).map(rate -> {
                rate.setRateDescription(newRate.getRateDescription());
                rate.setAmount(newRate.getAmount());
                rate.setRateExpirationDate(newRate.getRateExpirationDate());
                rate.setRateEffectiveDate(newRate.getRateExpirationDate());
                rate.setRateId(id);
                return ratesRepository.save(rate);
            }).orElseGet(() -> {
                throw new ResourceNotFoundException(environment.getProperty("not.found"));
            });
        } catch (InternalServerException exception) {
            throw new InternalServerException(environment.getProperty("internal.error"));
        }
    }

    @Override
    public void deleteRateById(Long rateId) {
        try {
            ratesRepository.deleteById(rateId);
        } catch (RuntimeException exception) {
            throw new ResourceNotFoundException(environment.getProperty("not.found"));
        }
    }
}
