package com.lab3.repository;

import com.lab3.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface CityRepository extends CrudRepository<City, Long> {

    Optional<City> findByZipCode(String zipCode);
    void deleteById(Long id);
    double averageDayTemperature(Long id, Date date);
}
