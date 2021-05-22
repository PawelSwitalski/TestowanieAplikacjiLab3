package com.lab3.services.impl;

import com.lab3.model.City;
import com.lab3.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CityRepositoryImplTest {

    CityRepository cityRepository;

    City zielona;
    City city2;
    City wroclaw;
    City warszawa;

    @BeforeEach
    void setUp() {
        cityRepository = new CityRepositoryImpl();
        zielona = new City("Zielona Gora", "1234");
        city2 = new City("miasto2", "111");
        wroclaw = new City("Wroclaw", "123");
        warszawa = new City("Warszawa", "32");


        cityRepository.save(zielona);
        cityRepository.save(city2);
        cityRepository.save(wroclaw);
        cityRepository.save(warszawa);

        for (int i = 0; i < 100; i++) {
            City city = new City("city" + i,
                    Integer.toString(10000 + i));
            cityRepository.save(city);
        }

    }

    @Test
    void findByZipCode() {
        Optional<City> city = cityRepository.findByZipCode("32");

        assertEquals("Warszawa", city.get().getCityName());
    }

    @Test
    void save() {
        City city = new City("neweMiasto", "097977");
        cityRepository.save(city);

        assertEquals(105, city.getId());
    }

    @Test
    void saveAll() {
        City city = new City("neweMiasto", "097977");
        City city2 = new City("neweMiasto2", "097978");
        List<City> cityList = new ArrayList<>();
        cityList.add(city);
        cityList.add(city2);
        cityRepository.saveAll(cityList);
        assertEquals("neweMiasto",
                cityRepository.findByZipCode(city.getZipCode()).get().getCityName());
        assertEquals("neweMiasto2",
                cityRepository.findByZipCode(city2.getZipCode()).get().getCityName());
    }

    @Test
    void findById() {
        Optional<City> city = cityRepository.findById(3L);
        assertEquals("Wroclaw", city.get().getCityName());
    }

    @Test
    void existsById() {
        assertTrue(cityRepository.existsById(12L));
    }

    @Test
    void findAll() {
        List<City> cityList = (List<City>) cityRepository.findAll();
        assertTrue(cityList.contains(zielona));
        assertTrue(cityList.contains(warszawa));
    }

    @Test
    void deleteById() {
        cityRepository.deleteById(zielona.getId());
        List<City> cityList = (List<City>) cityRepository.findAll();
        assertFalse(cityList.contains(zielona));
    }

    @Test
    void delete() {
        cityRepository.delete(zielona);
        List<City> cityList = (List<City>) cityRepository.findAll();
        assertFalse(cityList.contains(zielona));
    }

    @Test
    void deleteAll() {
        cityRepository.deleteAll();
        List<City> cityList = (List<City>) cityRepository.findAll();
        assertNull(cityList);
    }
}