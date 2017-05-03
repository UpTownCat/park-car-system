package com.example.dao;

import com.example.bean.ParkingPlace;
import com.example.bean.ParkingPlaceExample;
import com.example.bean.ParkingSeat;
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
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ParkingSeatMapperTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ParkingSeatMapper seatMapper;
    @Autowired
    private ParkingPlaceMapper placeMapper;

    @Test
    public void insert() throws Exception {
        ParkingPlaceExample example = new ParkingPlaceExample();
        List<ParkingPlace> places = placeMapper.selectByExample(example);
        for(int i = 0; i < places.size(); i++) {
            ParkingPlace place = places.get(i);
            for(int j = 0; j < 20; j++) {
                ParkingSeat seat = new ParkingSeat();
                seat.setParkingPlaceId(place.getId());
                seatMapper.insert(seat);
            }
        }
    }
}