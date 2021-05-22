package com.lab3;

import com.lab3.model.City;
import com.lab3.model.CityTemperature;

import java.util.LinkedList;
import java.util.List;

public final class Database {

    public List<City> getCityList() {
        return cityList;
    }


    public List<CityTemperature> getCityTemperatureList() {
        return cityTemperatureList;
    }




    public final static List<City> cityList = new LinkedList<>();
    public final static List<CityTemperature> cityTemperatureList = new LinkedList<>();



    public Database(){
    }





}
