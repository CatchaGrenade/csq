package com.learn.base.service.impl;

import com.learn.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Object key) {
        if (key == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int insertSelective(T entity) {
        //保存一个实体，null的属性不会保存，会使用数据库默认值
        return mapper.insertSelective(entity);
    }

    @Override
    public int save(T entity) {
        //保存一个实体，null的属性也会保存，不会使用数据库默认值
        return mapper.insert(entity);
    }

    @Override
    public int delete(Object key) {
        if (key == null) {
            return 0;
        }
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public void deleteByIds(List<String> ids, Class c, String priKey) {
        Example example = new Example(c);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(priKey, ids);
        mapper.deleteByExample(example);
    }

    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int selectCount(T example) {
        return mapper.selectCount(example);
    }


}