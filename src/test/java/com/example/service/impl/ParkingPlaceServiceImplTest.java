package com.example.service.impl;

import com.example.bean.ParkingPlace;
import com.example.bean.ParkingPlaceExample;
import com.example.service.ParkingPlaceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class ParkingPlaceServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ParkingPlaceService parkingPlaceService;
    @Test
    public void insert() throws Exception {
        ParkingPlace parkingPlace = new ParkingPlace();
        parkingPlace.setName("芳村隧道口停车场");
        parkingPlace.setLocation("芳村隧道口公交车站");
        parkingPlace.setMoneyPerHour(3.2);
        parkingPlaceService.insert(parkingPlace);
    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void updataByPrimaryKey() throws Exception {

    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        int id = 1;
        ParkingPlace parkingPlace = parkingPlaceService.selectByPrimaryKey(id);
        logger.info("parkingPlace = {}", parkingPlace);
    }

    @Test
    public void selectByExample() throws Exception {
        ParkingPlaceExample example = new ParkingPlaceExample();
        List<ParkingPlace> parkingPlaces = parkingPlaceService.selectByExample(example);
        for(int i = 0; i < parkingPlaces.size(); i++) {
            logger.info("parkingPlace = {}", parkingPlaces.get(i));
        }
    }
}