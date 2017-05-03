package com.example.service.impl;

import com.example.bean.CarOwner;
import com.example.bean.CarOwnerExample;
import com.example.dao.CarOwnerMapper;
import com.example.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    @Autowired
    private CarOwnerMapper mapper;
    @Override
    public int insert(CarOwner carOwner) {
        return mapper.insert(carOwner);
    }

    @Override
    public int insertSelective(CarOwner carOwner) {
        return mapper.insertSelective(carOwner);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(CarOwner carOwner) {
        return mapper.updateByPrimaryKey(carOwner);
    }

    @Override
    public int updateByPrimaryKeySelective(CarOwner carOwner) {
        return mapper.updateByPrimaryKeySelective(carOwner);
    }

    @Override
    public CarOwner selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CarOwner> selectByExample(CarOwnerExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public long countByExample(CarOwnerExample example) {
        return mapper.countByExample(example);
    }
}
