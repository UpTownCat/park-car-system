package com.example.dao;

import com.example.bean.ParkingPlace;
import com.example.bean.ParkingPlaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParkingPlaceMapper {
    long countByExample(ParkingPlaceExample example);

    int deleteByExample(ParkingPlaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ParkingPlace record);

    int insertSelective(ParkingPlace record);

    List<ParkingPlace> selectByExample(ParkingPlaceExample example);

    ParkingPlace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ParkingPlace record, @Param("example") ParkingPlaceExample example);

    int updateByExample(@Param("record") ParkingPlace record, @Param("example") ParkingPlaceExample example);

    int updateByPrimaryKeySelective(ParkingPlace record);

    int updateByPrimaryKey(ParkingPlace record);
}