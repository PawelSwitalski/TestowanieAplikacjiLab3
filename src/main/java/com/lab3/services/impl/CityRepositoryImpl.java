package com.lab3.services.impl;

import com.lab3.model.City;
import com.lab3.repository.CityRepository;
import com.lab3.repository.CityTemperatureRepository;
import com.lab3.services.CityService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CityRepositoryImpl implements CityRepository {
    //CityTemperatureRepository temperatureRep = new ;

    @Override
    public Optional<City> findByZipCode(String zipCode) {
        return Optional.empty();
    }

    @Override
    public <S extends City> S save(S s) {
        return null;
    }

    @Override
    public <S extends City> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<City> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<City> findAll() {
        return null;
    }

    @Override
    public Iterable<City> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public double averageDayTemperature(Long id, Date date) {

        return 0;
    }

    @Override
    public void delete(City city) {

    }

    @Override
    public void deleteAll(Iterable<? extends City> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
