package com.lab3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String cityName;
    private String zipCode;

    protected City() {}

    public City(String cityName, String zipCode) {
        this.cityName = cityName;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return String.format(
                "City[id=%d, cityName='%s', zipCode='%s']",
                id, cityName, zipCode);
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getZipCode() {
        return zipCode;
    }

;    public void setId(Long id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}