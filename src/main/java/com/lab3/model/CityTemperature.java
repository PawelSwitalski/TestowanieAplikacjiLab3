package com.lab3.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CityTemperature {
    @Id
    @GeneratedValue( strategy= GenerationType.AUTO )
    private int id;
    private double temperature;
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Date createDate;
    /*
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
     */
    @ManyToOne
    private City city;

    public CityTemperature(double temperature) {
        super();
        this.temperature = temperature;
    }

    public CityTemperature() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
