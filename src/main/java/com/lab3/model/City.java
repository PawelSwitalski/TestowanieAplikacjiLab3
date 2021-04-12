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

    public City(String firstName, String lastName) {
        this.cityName = firstName;
        this.zipCode = lastName;
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
}