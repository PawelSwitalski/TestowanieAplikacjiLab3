package com.lab3.services.impl;

import com.lab3.model.CityTemperature;
import com.lab3.repository.CityTemperatureRepository;

import java.util.Optional;

public class CityTemperatureImpl implements CityTemperatureRepository {
    @Override
    public <S extends CityTemperature> S save(S s) {
        return null;
    }

    @Override
    public <S extends CityTemperature> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<CityTemperature> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<CityTemperature> findAll() {
        return null;
    }

    @Override
    public Iterable<CityTemperature> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(CityTemperature cityTemperature) {

    }

    @Override
    public void deleteAll(Iterable<? extends CityTemperature> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
