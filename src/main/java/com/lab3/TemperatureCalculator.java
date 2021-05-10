package com.lab3;

import com.lab3.model.City;
import com.lab3.model.CityTemperature;
import com.lab3.repository.CityRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TemperatureCalculator {
    private final CityRepository cityRepository;

    public TemperatureCalculator(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public double dayAverage(City city, Date date){
        List<CityTemperature> temperatureList = cityRepository.cityTemperatures(city.getId());
        List<Double> todaysTemperature = new ArrayList<>();


        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

        for (CityTemperature ct :
                temperatureList) {
            if (fmt.format(ct.getCreateDate()).equals(fmt.format(date))) {
                todaysTemperature.add(ct.getTemperature());
            }
        }


        double allTemperature = 0.0;
        for (Double t :
                todaysTemperature) {
            allTemperature += t;
        }

        return allTemperature / todaysTemperature.size();
    }
}
