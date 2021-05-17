package com.lab3;

import com.lab3.model.City;
import com.lab3.model.CityTemperature;

import java.util.LinkedList;
import java.util.List;

public class Database {
    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<CityTemperature> getCityTemperatureList() {
        return cityTemperatureList;
    }

    public void setCityTemperatureList(List<CityTemperature> cityTemperatureList) {
        this.cityTemperatureList = cityTemperatureList;
    }

    List<City> cityList;
    List<CityTemperature> cityTemperatureList;

    public Database(){
        cityList = new LinkedList<>();
        cityTemperatureList = new LinkedList<>();
    }



}
