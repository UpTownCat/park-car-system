package com.example.dao;

import com.example.bean.CarOwner;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/5/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class CarOwnerMapperTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private  CarOwnerMapper carOwnerMapper;
    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        CarOwner owner = new CarOwner();
        owner.setName("一朵一朵朵花");
        owner.setGender(0);
        owner.setPassword("123456");
        owner.setPhone("18309226301");
        owner.setPhoto("123.jpg");
        carOwnerMapper.insert(owner);
        logger.info("owner = {}", owner);
    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }
}