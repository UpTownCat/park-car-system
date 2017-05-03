package com.example.bean;

import java.util.List;

public class ParkingPlace {
    private Integer id;

    private String name;

    private String location;

    private Double moneyPerHour;

    private Long availableSeat;

    private List<ParkingSeat> parkingSeats;

    public List<ParkingSeat> getParkingSeats() {
        return parkingSeats;
    }

    public void setParkingSeats(List<ParkingSeat> parkingSeats) {
        this.parkingSeats = parkingSeats;
    }

    public Long getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(Long availableSeat) {
        this.availableSeat = availableSeat;
    }

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

    public Double getMoneyPerHour() {
        return moneyPerHour;
    }

    public void setMoneyPerHour(Double moneyPerHour) {
        this.moneyPerHour = moneyPerHour;
    }
}