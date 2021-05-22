package com.lab3;

import com.lab3.model.City;
import com.lab3.model.CityTemperature;
import com.lab3.repository.CityRepository;
import com.lab3.repository.CityTemperatureRepository;
import com.lab3.services.impl.CityRepositoryImpl;
import com.lab3.services.impl.CityTemperatureImpl;
import org.junit.jupiter.api.AfterEach;
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

class TemperatureCalculatorDBTest {

    CityRepository cityRepository = new CityRepositoryImpl();
    CityTemperatureRepository cityTemperatureRepository = new CityTemperatureImpl();

    TemperatureCalculator calculator;

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

        list.add(new CityTemperature(30,
                new Date(1998, Calendar.APRIL, 10, 12,1,34)));
        list.add(new CityTemperature(-3.3,
                new Date(1998, Calendar.APRIL, 10, 1,1,3)));
        list.add(new CityTemperature(3.3,
                new Date(1998, Calendar.APRIL, 10, 3,1,3)));

        list.add(new CityTemperature(-10,
                new Date(1999, Calendar.FEBRUARY, 10, 12,1,34)));
        list.add(new CityTemperature(-3.3,
                new Date(1999, Calendar.FEBRUARY, 10, 1,1,3)));
        list.add(new CityTemperature(-4.3,
                new Date(1999, Calendar.FEBRUARY, 10, 3,1,3)));

        list.add(new CityTemperature(30,
                new Date(1999, Calendar.JUNE, 10)));
        list.add(new CityTemperature(31,
                new Date(1999, Calendar.JUNE, 13)));
        list.add(new CityTemperature(25,
                new Date(1999, Calendar.JUNE, 1)));
        list.add(new CityTemperature(34,
                new Date(1999, Calendar.JUNE, 12)));
        list.add(new CityTemperature(27,
                new Date(1999, Calendar.JUNE, 27)));

        list.add(new CityTemperature(30,
                new Date(1999, Calendar.JULY, 10)));
        list.add(new CityTemperature(31,
                new Date(1999, Calendar.JULY, 13)));
        list.add(new CityTemperature(25,
                new Date(1999, Calendar.JULY, 14)));
        list.add(new CityTemperature(34,
                new Date(1999, Calendar.JULY, 19)));
        list.add(new CityTemperature(27,
                new Date(1999, Calendar.JULY, 27)));

        list.forEach(cityTemperature -> cityTemperature.setCity(city));

        return list;
    }

    @BeforeEach
    void setUP() throws Exception {
        city = new City("Zielona", "12345");
        city.setId(1L);
        cityRepository.save(city);
        cityRepository.save(new City("Zielona2", "12345"));

        cityTemperatures = setUPTemperature();
        cityTemperatureRepository.saveAll(cityTemperatures);

        calculator = new TemperatureCalculator(new CityRepositoryImpl());
    }

    @AfterEach
    void reset(){
        cityTemperatureRepository.deleteAll();
        cityRepository.deleteAll();
    }

    @Test
    void dailyAverage() {
        assertEquals(21.5, calculator.dailyAverage(city, new Date()));
    }

    @Test
    void dailyAverage2() {
        CityTemperature cityTemperature = new CityTemperature(30,
                new Date(1999, Calendar.JULY, 10));

        assertEquals(21.5, calculator.dailyAverage(city, new Date()));
    }

    @Test
    void dailyHighestTemperatureTest() {
        assertEquals(temp1, calculator.dailyHighestTemperature(city, new Date()));
    }

    @Test
    void dailyHighestTemperatureTest2() {
        double tem = 40.9;
        CityTemperature cityTemperature = new CityTemperature(tem, new Date());
        cityTemperature.setCity(city);
        cityTemperatureRepository.save(cityTemperature);
        assertEquals(tem, calculator.dailyHighestTemperature(city, new Date()));
    }

    @Test
    void dailyHighestTemperatureTest3() {
        double tem = -3.2;
        cityTemperatures.add(new CityTemperature(tem, new Date()));
        assertEquals(temp1, calculator.dailyHighestTemperature(city, new Date()));
    }

    @Test
    void dailyLowestTemperatureTest() {
        assertEquals(temp2, calculator.dailyLowestTemperature(city, new Date()));
    }

    @Test
    void dailyLowestTemperatureTest2() {
        double tem = 40.9;
        cityTemperatures.add(new CityTemperature(tem, new Date()));
        assertEquals(temp2, calculator.dailyLowestTemperature(city, new Date()));
    }

    @Test
    void dailyLowestTemperatureTest3() {
        double tem = -3.2;
        CityTemperature cityTemperature = new CityTemperature(tem, new Date());
        cityTemperature.setCity(city);
        cityTemperatureRepository.save(cityTemperature);
        assertEquals(tem, calculator.dailyLowestTemperature(city, new Date()));
    }

    @Test
    void monthlyAverageTest() {
        assertEquals(29.4, calculator.monthlyAverage(city, new Date(1999, Calendar.JUNE, 2)));
    }

    @Test
    void dailyAmplitudeTest() {
        assertEquals(3.3, calculator.dailyAmplitude(city, new Date()));
    }

    @Test
    void dailyAmplitudeTest2() {
        assertEquals(33.3, calculator.dailyAmplitude(city, new Date(1998, Calendar.APRIL, 10)));
    }

    @Test
    void dailyAmplitudeTest3() {
        assertEquals(6.7, calculator.dailyAmplitude(city, new Date(1999, Calendar.FEBRUARY, 10)));
    }

    @Test
    void temperatureBetweenTest() {
        List<CityTemperature> testList = new LinkedList<>();

        testList.add(new CityTemperature(31,
                new Date(1999, Calendar.JULY, 13)));
        testList.add(new CityTemperature(25,
                new Date(1999, Calendar.JULY, 14)));
        testList.add(new CityTemperature(34,
                new Date(1999, Calendar.JULY, 19)));

        List<CityTemperature> testResult = calculator.temperatureBetween(
                city,
                new Date(1999, Calendar.JULY, 12),
                new Date(1999, Calendar.JULY, 20)
        );
        assertEquals(testList.size(), testResult.size());
    }

    @Test
    void biggerThanTest() {
        List<CityTemperature> testList = new LinkedList<>();

        testList.add(new CityTemperature(30,
                new Date(1999, Calendar.JULY, 10)));
        testList.add(new CityTemperature(31,
                new Date(1999, Calendar.JULY, 13)));
        testList.add(new CityTemperature(34,
                new Date(1999, Calendar.JULY, 19)));

        List<CityTemperature> testResult = calculator.biggerThan(
                29,
                city,
                new Date(1999, Calendar.JUNE, 30),
                new Date(1999, Calendar.AUGUST, 1)
        );

        assertEquals(testList.size(), testResult.size());
    }


    @Test
    void biggerThanTest2() {
        List<CityTemperature> testList = new LinkedList<>();

        //cityTemperatures.add(new CityTemperature(40,
        //        new Date(1999, Calendar.MAY, 5)));
        CityTemperature cityTemperature = new CityTemperature(40,
                new Date(1999, Calendar.MAY, 5));
        cityTemperature.setCity(city);
        cityTemperatureRepository.save(cityTemperature);


        testList.add(new CityTemperature(30,
                new Date(1999, Calendar.JULY, 10)));
        testList.add(new CityTemperature(31,
                new Date(1999, Calendar.JULY, 13)));
        testList.add(new CityTemperature(34,
                new Date(1999, Calendar.JULY, 19)));
        testList.add(new CityTemperature(40,
                new Date(1999, Calendar.MAY, 5)));
        testList.add(new CityTemperature(30,
                new Date(1998, Calendar.APRIL, 10, 12,1,34)));
        testList.add(new CityTemperature(30,
                new Date(1999, Calendar.JUNE, 10)));
        testList.add(new CityTemperature(31,
                new Date(1999, Calendar.JUNE, 13)));
        testList.add(new CityTemperature(34,
                new Date(1999, Calendar.JUNE, 12)));

        List<CityTemperature> testResult = calculator.biggerThan(
                29,
                city
        );

        assertEquals(testList.size(), testResult.size());
    }
}