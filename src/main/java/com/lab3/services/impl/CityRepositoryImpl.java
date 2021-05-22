package com.lab3.services.impl;

import com.lab3.Database;
import com.lab3.model.City;
import com.lab3.model.CityTemperature;
import com.lab3.repository.CityRepository;
import com.lab3.repository.CityTemperatureRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CityRepositoryImpl implements CityRepository {
    Database db = new Database();

    @Override
    public Optional<City> findByZipCode(String zipCode) {
        if (db.getCityList().size() >= 1){
            for (City city :
                    db.getCityList()) {
                if (city.getZipCode().equals(zipCode))
                    return Optional.of(city);
            }
        }
        return Optional.empty();
    }

    @Override
    public City save(City s) {
        /*
        if (s.getId() == null){
            Long lastId = db.getCityList().get(db.getCityList().size() - 1).getId() + 1;

        }

        return null;
        */


        try {
            s.setId(db.getCityList().get(db.getCityList().size() - 1).getId() + 1);
            db.getCityList().add(s);
            return s;
        } catch (IndexOutOfBoundsException e){
            s.setId(1L);
        }
        //db.getCityList().add(s);
        return null;
    }

    @Override
    public <S extends City> Iterable<S> saveAll(Iterable<S> iterable) {
        for (S city :
                iterable) {
            save(city);
        }
        return null;
    }

    @Override
    public Optional<City> findById(Long aLong) {
        if (db.getCityList().size() >= 1){
            for (City city :
                    db.getCityList()) {
                if (city.getId().equals(aLong))
                    return Optional.of(city);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        if (db.getCityList().size() >= 1){
            for (City city :
                    db.getCityList()) {
                if (city.getId().equals(aLong))
                    return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<City> findAll() {
        if (!db.getCityList().isEmpty()) return db.getCityList();
        return null;
    }

    @Override
    public Iterable<City> findAllById(Iterable<Long> iterable) {
        List<City> cities = new LinkedList<>();
        if (!db.getCityList().isEmpty()){
            for (City city :
                    db.getCityList()) {
                for (Long i :
                        iterable) {
                    if (city.getId().equals(i)) cities.add(city);
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
    public void deleteById(Long id) {
        db.getCityList().removeIf(city -> city.getId().equals(id));
    }

    @Override
    public List<CityTemperature> cityTemperatures(Long id) {
        List<CityTemperature> cityTemperatures = new LinkedList<>();
        for (CityTemperature c :
                db.getCityTemperatureList()) {
            if (c.getCity().getId().equals(id))
                cityTemperatures.add(c);
        }

        if (!cityTemperatures.isEmpty())
            return cityTemperatures;
        return null;
    }

    /*
    @Override
    public double averageDayTemperature(Long id, Date date) {

        return 0;
    }

    @Override
    public double averageMonthTemperature(Long id, short month) {
        return 0;
    }

     */

    @Override
    public void delete(City city) {
        deleteById(city.getId());
    }

    @Override
    public void deleteAll(Iterable<? extends City> iterable) {
        if (!db.getCityList().isEmpty()){
            for (City city :
                    db.getCityList()) {
                for (City i :
                        iterable) {
                    if (city.getId().equals(i.getId())) db.getCityList().removeIf(c -> c.getId().equals(i.getId()));;
                }
            }
        }

    }

    @Override
    public void deleteAll() {
        db.getCityList().clear();
    }
}
