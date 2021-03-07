package com.dpworld.rms.controller;

import com.dpworld.rms.dto.RateAndSurcharge;
import com.dpworld.rms.model.Rate;
import com.dpworld.rms.service.RatesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rates")
public class RatesController {
    private static final Logger logger = LoggerFactory.getLogger(RatesController.class);
    private final RatesServiceImpl ratesService;

    public RatesController(RatesServiceImpl ratesService) {
        this.ratesService = ratesService;
    }

    @GetMapping(value = "")
    public Iterable<Rate> getRates() {
        logger.debug("Retrieving all rates");
        return ratesService.getRates();
    }

    @GetMapping(value = "/{id}")
    public RateAndSurcharge getRateById(@PathVariable Long id) {
        logger.debug("Searching rate for Id:" + id);
        return ratesService.findRateById(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<Rate> addRate(@RequestBody Rate rate) {
        rate = ratesService.addRate(rate);
        logger.debug("Added a new rate");
        return new ResponseEntity<>(rate, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Rate> updateRateById(@PathVariable Long id, @RequestBody Rate rate) {
        rate = ratesService.updateRateById(id, rate);
        logger.debug("Updated rate for Id:" + id);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteRateById(@PathVariable Long id) {
        ratesService.deleteRateById(id);
        logger.debug("Deleted rate for Id:" + id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }
}
