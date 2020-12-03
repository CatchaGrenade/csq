package com.learn.services.impl;

import com.learn.dao.A_0001Dao;
import com.learn.model.A_0001;
import com.learn.services.A_0001Sv;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class A1_0001SvImpl implements A_0001Sv {

    @Resource
    A_0001Dao a_0001Dao;

    public List<A_0001> findAll() {
        return a_0001Dao.findAll();
    }
}
