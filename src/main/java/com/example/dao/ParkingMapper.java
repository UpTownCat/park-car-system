package com.example.dao;

import com.example.bean.Parking;
import com.example.bean.ParkingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParkingMapper {
    long countByExample(ParkingExample example);

    int deleteByExample(ParkingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Parking record);

    int insertSelective(Parking record);

    List<Parking> selectByExample(ParkingExample example);

    Parking selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Parking record, @Param("example") ParkingExample example);

    int updateByExample(@Param("record") Parking record, @Param("example") ParkingExample example);

    int updateByPrimaryKeySelective(Parking record);

    int updateByPrimaryKey(Parking record);
}