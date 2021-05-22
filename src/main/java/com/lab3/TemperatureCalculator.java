package com.lab3;

import com.lab3.model.City;
import com.lab3.model.CityTemperature;
import com.lab3.repository.CityRepository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class TemperatureCalculator {
    private final CityRepository cityRepository;

    public TemperatureCalculator(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public double dailyAverage(City city, Date date){
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

    public double dailyAmplitude(City city, Date date) {
        return BigDecimal.valueOf(dailyHighestTemperature(city, date))
                .subtract(BigDecimal.valueOf(dailyLowestTemperature(city, date)))
                .doubleValue();
    }

    public double monthlyAverage(City city, Date date) {
        List<CityTemperature> temperatureList = cityRepository.cityTemperatures(city.getId());
        List<Double> todaysTemperature = new ArrayList<>();


        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");

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

    public double dailyHighestTemperature(City city, Date date){
        List<CityTemperature> temperatureList = cityRepository.cityTemperatures(city.getId());
        List<Double> todaysTemperature = new ArrayList<>();


        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

        for (CityTemperature ct :
                temperatureList) {
            if (fmt.format(ct.getCreateDate()).equals(fmt.format(date))) {
                todaysTemperature.add(ct.getTemperature());
            }
        }

        if (todaysTemperature.isEmpty())
            return -999;

        double temperature = todaysTemperature.get(0);
        for (Double t:
                todaysTemperature) {
            if (temperature < t)
                temperature = t;
        }

        return temperature;
    }

    public double dailyLowestTemperature(City city, Date date){
        List<CityTemperature> temperatureList = cityRepository.cityTemperatures(city.getId());
        List<Double> todaysTemperature = new ArrayList<>();


        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

        for (CityTemperature ct :
                temperatureList) {
            if (fmt.format(ct.getCreateDate()).equals(fmt.format(date))) {
                todaysTemperature.add(ct.getTemperature());
            }
        }

        if (todaysTemperature.isEmpty())
            return 999;

        double temperature = todaysTemperature.get(0);
        for (Double t:
                todaysTemperature) {
            if (temperature > t)
                temperature = t;
        }

        return temperature;
    }

    /**
     *
     * @param temperature temperatura od której będą zliczane rekordy
     * @param city
     * @return
     */
    public List<CityTemperature> biggerThan(double temperature, City city){
        return biggerThan(
                temperature,
                city,
                new Date(1, Calendar.JANUARY, 1),
                new Date(2199, Calendar.DECEMBER, 31)
        );
    }

    /**
     *
     * @param temperature temperatura od której będą zliczane rekordy
     * @param city miasto z którego bierzemy temperatury
     * @param fromDate data od której zliczamy informacje
     * @param toDate data do której zliczamy informacje
     * @return lista temperatur większych niż @param temperature
     */
    public List<CityTemperature> biggerThan(
            double temperature,
            City city,
            Date fromDate,
            Date toDate)
    {
        return temperatureBetween(city, fromDate, toDate).stream()
                .filter(cityTemperature -> cityTemperature.getTemperature() > temperature)
                .collect(Collectors.toList());
    }


    /**
     *
     * @param city miasto z którego bierzemy temperatury
     * @param fromDate data od której zliczamy informacje
     * @param toDate data do której zliczamy informacje
     * @return lista temperatur pomiędzy fromDate i toDate
     */
    public List<CityTemperature> temperatureBetween(City city, Date fromDate, Date toDate)
    {
        return cityRepository.cityTemperatures(city.getId()).stream()
                .filter(cityTemperature -> cityTemperature.getCreateDate().after(fromDate))
                .filter(cityTemperature -> cityTemperature.getCreateDate().before(toDate))
                .collect(Collectors.toList());
    }
}
