package com.dpworld.rms.repository;

import com.dpworld.rms.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatesRepository extends CrudRepository<Rate, Long> {
    //Writing custom method for writing unit test case
    Optional<Rate> findByRateId(Long id);
}
