package com.example.dao;

import com.example.bean.CarOwner;
import com.example.bean.CarOwnerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CarOwnerMapper {
    long countByExample(CarOwnerExample example);

    int deleteByExample(CarOwnerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CarOwner record);

    int insertSelective(CarOwner record);

    List<CarOwner> selectByExample(CarOwnerExample example);

    CarOwner selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CarOwner record, @Param("example") CarOwnerExample example);

    int updateByExample(@Param("record") CarOwner record, @Param("example") CarOwnerExample example);

    int updateByPrimaryKeySelective(CarOwner record);

    int updateByPrimaryKey(CarOwner record);
}