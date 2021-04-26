package com.lab3;

import com.lab3.model.City;
import com.lab3.model.CityTemperature;
import com.lab3.repository.CityRepository;
import com.lab3.services.impl.CityRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class CityTest {

    @InjectMocks
    CityRepositoryImpl cityService;

    @Mock
    CityRepository cityRepository;

    City city;

    List<CityTemperature> cityTemperatures = new LinkedList<>();

    private final double temp1 = 23.3;
    private final double temp2 = 20;
    private final double temp3 = 21.2;

    private List<CityTemperature> setUPTemperature(){
        List<CityTemperature> list = new LinkedList<>();
        list.add(new CityTemperature(temp1));
        list.add(new CityTemperature(temp2));
        list.add(new CityTemperature(temp3));
        return list;
    }

    @BeforeEach
    void setUP() throws Exception {
        city = new City("Zielona", "12345");
        city.setId(1L);
        cityTemperatures = setUPTemperature();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCityName() {
        when(cityRepository.findByZipCode( anyString())).thenReturn(java.util.Optional.of(city));
        Optional<City> newCity = cityRepository.findByZipCode("a");
        assertEquals("Zielona", newCity.get().getCityName());
    }

    @Test
    void findByIdTest() {
        when(cityRepository.findById(anyLong())).thenReturn(java.util.Optional.of(city));
        Optional<City> newCity = cityRepository.findById(32L);
        assertEquals("12345", newCity.get().getZipCode());
    }

    @Test
    void averageDayTemperatureTest() {
        when(cityRepository.averageDayTemperature(anyLong(), any()))
                .thenReturn((temp1 + temp2 + temp3) / 3);

        assertEquals(21.5, cityRepository.averageDayTemperature(12L, new Date()));
    }

    @Test
    void deleteCityTest(){
        //when(cityRepository.deleteById(1L)).thenReturn(return null);
        cityRepository.deleteById(1L);
        assertNull(city);
    }

}
