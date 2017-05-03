package com.example.bean;

import java.util.Date;

public class Parking {
    private Integer id;

    private Integer parkingSeatId;

    private Integer carId;

    private Date inTime;

    private Date outTime;

    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParkingSeatId() {
        return parkingSeatId;
    }

    public void setParkingSeatId(Integer parkingSeatId) {
        this.parkingSeatId = parkingSeatId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}