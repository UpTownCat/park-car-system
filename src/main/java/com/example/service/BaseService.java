package com.example.service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
public interface BaseService<T, E> {
    int insert(T t);

    int insertSelective(T t);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(T t);

    int updateByPrimaryKeySelective(T t);

    T selectByPrimaryKey(Integer id);

    List<T> selectByExample(E e);

    long countByExample(E e);
}
