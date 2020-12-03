package com.learn.base.service;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface BaseService<T> {

    T selectByKey(Object key);

    /**
     * 描述 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    int insertSelective(T entity);

    /**
     * 描述
     */
    int save(T entity);

    int delete(Object key);

    void deleteByIds(List<String> ids, Class c,String priKey);

    int updateAll(T entity);

    int updateNotNull(T entity);

    int selectCount(T example);

}
