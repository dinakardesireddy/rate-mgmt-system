package com.dpworld.rms.service;

import com.dpworld.rms.dto.RateAndSurcharge;
import com.dpworld.rms.model.Rate;

public interface RatesService {
    Iterable<Rate> getRates();
    RateAndSurcharge findRateById(Long id);
    Rate addRate(Rate rate);
    Rate updateRateById(Long Id, Rate rate);
    void deleteRateById(Long rateId);
}
