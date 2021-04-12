package com.lab3.repository;

import com.lab3.model.CityTemperature;
import org.springframework.data.repository.CrudRepository;

public interface CityTemperatureRepository extends CrudRepository<CityTemperature, Long> {
}
