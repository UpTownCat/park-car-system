package com.example.service.impl;

import com.example.bean.Car;
import com.example.bean.CarExample;
import com.example.dao.CarMapper;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarMapper mapper;
    @Override
    public int insert(Car car) {
        return mapper.insert(car);
    }

    @Override
    public int insertSelective(Car car) {
        return mapper.insertSelective(car);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Car car) {
        return mapper.updateByPrimaryKey(car);
    }

    @Override
    public int updateByPrimaryKeySelective(Car car) {
        return mapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public Car selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Car> selectByExample(CarExample carExample) {
        return mapper.selectByExample(carExample);
    }

    @Override
    public long countByExample(CarExample example) {
        return mapper.countByExample(example);
    }
}
