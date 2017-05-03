package com.example.service.impl;

import com.example.bean.ParkingSeat;
import com.example.bean.ParkingSeatExample;
import com.example.dao.ParkingSeatMapper;
import com.example.service.ParkingSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class ParkingSeatServiceImpl implements ParkingSeatService{
    @Autowired
    private ParkingSeatMapper mapper;
    @Override
    public int insert(ParkingSeat parkingSeat) {
        return mapper.insert(parkingSeat);
    }

    @Override
    public int insertSelective(ParkingSeat parkingSeat) {
        return mapper.insertSelective(parkingSeat);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(ParkingSeat parkingSeat) {
        return mapper.updateByPrimaryKey(parkingSeat);
    }

    @Override
    public int updateByPrimaryKeySelective(ParkingSeat parkingSeat) {
        return mapper.updateByPrimaryKeySelective(parkingSeat);
    }

    @Override
    public ParkingSeat selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ParkingSeat> selectByExample(ParkingSeatExample parkingSeatExample) {
        return mapper.selectByExample(parkingSeatExample);
    }

    @Override
    public long countByExample(ParkingSeatExample parkingSeatExample) {
        return mapper.countByExample(parkingSeatExample);
    }
}
