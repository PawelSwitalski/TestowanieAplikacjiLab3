package com.lab3.repository;

import com.lab3.model.City;
import com.lab3.model.CityTemperature;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CityRepository{

    Optional<City> findByZipCode(String zipCode);
    void deleteById(Long id);
    List<CityTemperature> cityTemperatures(Long id);
    public City save(City s);
    public <S extends City> Iterable<S> saveAll(Iterable<S> iterable);
    public Optional<City> findById(Long aLong);
    boolean existsById(Long aLong);
    public Iterable<City> findAll();
    public Iterable<City> findAllById(Iterable<Long> iterable);
    public long count();
    void delete(City city);
    void deleteAll(Iterable<? extends City> iterable);
    void deleteAll();
}
