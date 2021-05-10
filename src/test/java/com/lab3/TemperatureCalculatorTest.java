package com.lab3;

import com.lab3.model.City;
import com.lab3.model.CityTemperature;
import com.lab3.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class TemperatureCalculatorTest {

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
    void dailyAverage() {
        when(cityRepository.cityTemperatures( anyLong())).thenReturn(cityTemperatures);
        TemperatureCalculator calculator = new TemperatureCalculator(cityRepository);

        assertEquals(21.5, calculator.dayAverage(city, new Date()));
    }

    @Test
    void dailyAverage2() {
        when(cityRepository.cityTemperatures( anyLong())).thenReturn(cityTemperatures);
        TemperatureCalculator calculator = new TemperatureCalculator(cityRepository);
        cityTemperatures.add(new CityTemperature(30,
                new Date(1999, Calendar.JULY, 10)));

        assertEquals(21.5, calculator.dayAverage(city, new Date()));
    }
}