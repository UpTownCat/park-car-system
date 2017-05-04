package com.example.dao;

import com.example.bean.ParkingPlace;
import com.example.util.CommonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ParkingPlaceMapperTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private  ParkingPlaceMapper parkingPlaceMapper;
    @Test
    public void countByExample() throws Exception {

    }

    @Test
    public void deleteByExample() throws Exception {

    }

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        for (int i = 0; i < 30; i++) {
            ParkingPlace place = new ParkingPlace();
            place.setName("大学城停车场" + i);
            place.setLocation("西安未央大学城" + i);
            place.setMoneyPerHour(3 + i * 0.1);
            System.out.println(CommonUtil.formatDouble(place.getMoneyPerHour()));
        }
    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void selectByExample() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void updateByExampleSelective() throws Exception {

    }

    @Test
    public void updateByExample() throws Exception {

    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }
}