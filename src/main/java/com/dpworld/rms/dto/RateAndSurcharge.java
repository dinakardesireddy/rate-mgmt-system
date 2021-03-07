package com.dpworld.rms.dto;

import com.dpworld.rms.model.Rate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
@Component
public class RateAndSurcharge {
    private Optional<Rate> rate;
    private Map<String, String> surcharge;

    public RateAndSurcharge(Optional<Rate> rate, Map<String, String> surcharge) {
        this.rate = rate;
        this.surcharge = surcharge;
    }

    public RateAndSurcharge() {
    }

    public Map<String, String> getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Map<String, String> surcharge) {
        this.surcharge = surcharge;
    }

    public Optional<Rate> getRate() {
        return rate;
    }

    public void setRate(Optional<Rate> rate) {
        this.rate = rate;
    }
}
