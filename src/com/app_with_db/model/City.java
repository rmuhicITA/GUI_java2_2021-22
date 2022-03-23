package com.app_with_db.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.StringJoiner;

public class City implements Serializable {

    private int cityId;
    private String city;
    private int country_id;
    private Timestamp last_update;

    public City() {
        super();
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public String toString() {
        return new StringJoiner(", ")
                .add(cityId + "")
                .add(city + "")
                .add(country_id + "")
                .add(last_update + "")
                .toString();
    }
}
