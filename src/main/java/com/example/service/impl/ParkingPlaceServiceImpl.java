package com.example.service.impl;

import com.example.bean.ParkingPlace;
import com.example.bean.ParkingPlaceExample;
import com.example.dao.ParkingPlaceMapper;
import com.example.service.ParkingPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class ParkingPlaceServiceImpl implements ParkingPlaceService{
    @Autowired
    private ParkingPlaceMapper mapper;
    @Override
    public int insert(ParkingPlace parkingPlace) {
        return mapper.insert(parkingPlace);
    }

    @Override
    public int insertSelective(ParkingPlace parkingPlace) {
        return mapper.insertSelective(parkingPlace);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(ParkingPlace parkingPlace) {
        return mapper.updateByPrimaryKey(parkingPlace);
    }

    @Override
    public int updateByPrimaryKeySelective(ParkingPlace parkingPlace) {
        return mapper.updateByPrimaryKeySelective(parkingPlace);
    }

    @Override
    public ParkingPlace selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ParkingPlace> selectByExample(ParkingPlaceExample parkingPlaceExample) {
        return mapper.selectByExample(parkingPlaceExample);
    }

    @Override
    public long countByExample(ParkingPlaceExample parkingPlaceExample) {
        return mapper.countByExample(parkingPlaceExample);
    }
}
