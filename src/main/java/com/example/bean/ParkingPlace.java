package com.example.bean;

public class ParkingPlace {
    private Integer id;

    private String name;

    private String location;

    private Double money_per_hour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Double getMoney_per_hour() {
        return money_per_hour;
    }

    public void setMoney_per_hour(Double money_per_hour) {
        this.money_per_hour = money_per_hour;
    }
}