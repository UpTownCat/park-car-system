package com.example.dao;

import com.example.bean.ParkingSeat;
import com.example.bean.ParkingSeatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ParkingSeatMapper {
    long countByExample(ParkingSeatExample example);

    int deleteByExample(ParkingSeatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ParkingSeat record);

    int insertSelective(ParkingSeat record);

    List<ParkingSeat> selectByExample(ParkingSeatExample example);

    ParkingSeat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ParkingSeat record, @Param("example") ParkingSeatExample example);

    int updateByExample(@Param("record") ParkingSeat record, @Param("example") ParkingSeatExample example);

    int updateByPrimaryKeySelective(ParkingSeat record);

    int updateByPrimaryKey(ParkingSeat record);
}