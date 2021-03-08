package com.dpworld.rms.service;

import com.dpworld.rms.dto.RateAndSurcharge;
import com.dpworld.rms.exception.InternalServerException;
import com.dpworld.rms.exception.ResourceNotFoundException;
import com.dpworld.rms.model.Rate;
import com.dpworld.rms.repository.RatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:rmserrors.properties")
public class RatesServiceImpl implements RatesService {
    private final RatesRepository ratesRepository;
    private final SurchargeServiceImpl surchargeServiceImpl;
    private final RateAndSurcharge rateAndSurcharge;
    @Value("${not.found}")
    private String notFound;
    @Value("${internal.error}")
    private String internalError;
    private static final Logger logger = LoggerFactory.getLogger(RatesServiceImpl.class);
    public RatesServiceImpl(RatesRepository ratesRepository,
                            SurchargeServiceImpl surchargeServiceImpl, RateAndSurcharge rateAndSurcharge) {
        this.ratesRepository = ratesRepository;
        this.surchargeServiceImpl = surchargeServiceImpl;
        this.rateAndSurcharge = rateAndSurcharge;
    }
    @Override
    public RateAndSurcharge findRateById(Long rateId) {
        rateAndSurcharge.setRate(ratesRepository.findByRateId(rateId));
        rateAndSurcharge.setSurcharge(surchargeServiceImpl.getSurcharge());
        if (!rateAndSurcharge.getRate().isPresent()) {
            throw new ResourceNotFoundException(notFound);
        }
        return rateAndSurcharge;
    }
    @Override
    public Iterable<Rate> getRates() {
        try {
            return ratesRepository.findAll();
        } catch (RuntimeException exception) {
            logger.error(exception.getMessage());
            throw new InternalServerException(internalError);
        }
    }
    @Override
    public Rate addRate(Rate rate) {
        try {
            return ratesRepository.save(rate);
        } catch (RuntimeException exception) {
            logger.error(exception.getMessage());
            throw new InternalServerException(internalError);
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
                throw new ResourceNotFoundException(notFound);
            });
        } catch (InternalServerException exception) {
            logger.error(exception.getMessage());
            throw new InternalServerException(internalError);
        }
    }

    @Override
    public void deleteRateById(Long rateId) {
        try {
            ratesRepository.deleteById(rateId);
        } catch (RuntimeException exception) {
            logger.error(exception.getMessage());
            throw new ResourceNotFoundException(notFound);
        }
    }
}
