package com.example.service.impl;

import com.example.bean.Parking;
import com.example.bean.ParkingExample;
import com.example.dao.ParkingMapper;
import com.example.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class ParkingServiceImpl implements ParkingService{
    @Autowired
    private ParkingMapper mapper;
    @Override
    public int insert(Parking parking) {
        return mapper.insert(parking);
    }

    @Override
    public int insertSelective(Parking parking) {
        return mapper.insert(parking);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Parking parking) {
        return mapper.updateByPrimaryKey(parking);
    }

    @Override
    public int updateByPrimaryKeySelective(Parking parking) {
        return mapper.updateByPrimaryKeySelective(parking);
    }

    @Override
    public Parking selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Parking> selectByExample(ParkingExample parkingExample) {
        return mapper.selectByExample(parkingExample);
    }

    @Override
    public long countByExample(ParkingExample parkingExample) {
        return mapper.countByExample(parkingExample);
    }
}
