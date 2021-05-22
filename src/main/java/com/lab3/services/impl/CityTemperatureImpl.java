package com.lab3.services.impl;

import com.lab3.Database;
import com.lab3.model.City;
import com.lab3.model.CityTemperature;
import com.lab3.repository.CityTemperatureRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CityTemperatureImpl implements CityTemperatureRepository {
    Database db = new Database();

    @Override
    public <S extends CityTemperature> S save(S s) {
        try {
            s.setId(db.getCityTemperatureList().get(db.getCityTemperatureList().size() - 1).getId() + 1);
        } catch (IndexOutOfBoundsException e){
            s.setId(1);
        }
        db.getCityTemperatureList().add(s);
        return null;
    }

    @Override
    public <S extends CityTemperature> Iterable<S> saveAll(Iterable<S> iterable) {
        for (S cityTemperature :
                iterable) {
            save(cityTemperature);
        }
        return null;
    }

    @Override
    public Optional<CityTemperature> findById(Long aLong) {
        if (db.getCityTemperatureList().size() >= 1){
            for (CityTemperature city :
                    db.getCityTemperatureList()) {
                if (city.getId() == aLong)
                    return Optional.of(city);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        if (db.getCityTemperatureList().size() >= 1){
            for (CityTemperature city :
                    db.getCityTemperatureList()) {
                if (city.getId() == aLong)
                    return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<CityTemperature> findAll() {
        if (!db.getCityTemperatureList().isEmpty()) return db.getCityTemperatureList();
        return null;
    }

    @Override
    public Iterable<CityTemperature> findAllById(Iterable<Long> iterable) {
        List<CityTemperature> cities = new LinkedList<>();
        if (!db.getCityTemperatureList().isEmpty()){
            for (CityTemperature city :
                    db.getCityTemperatureList()) {
                for (Long i :
                        iterable) {
                    if (city.getId() == i) cities.add(city);
                }
            }
            if (!cities.isEmpty())
                return cities;
        }
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        db.getCityTemperatureList().removeIf(city -> city.getId() == aLong);
    }

    @Override
    public void delete(CityTemperature cityTemperature) {
        deleteById((long) cityTemperature.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends CityTemperature> iterable) {
        if (!db.getCityTemperatureList().isEmpty()){
            for (CityTemperature city :
                    db.getCityTemperatureList()) {
                for (CityTemperature i :
                        iterable) {
                    if (city.getId() == i.getId()) db.getCityList().removeIf(c -> c.getId().equals(i.getId()));;
                }
            }
        }
    }

    @Override
    public void deleteAll() {
        db.getCityTemperatureList().clear();
    }
}
