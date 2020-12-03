package com.learn.services.impl;

import com.learn.base.service.impl.BaseServiceImpl;
import com.learn.dao.UserDao;
import com.learn.model.UserModel;
import com.learn.services.UserSv;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserSvImpl extends BaseServiceImpl<UserModel> implements UserSv{

    @Resource
    UserDao userDao;
    public List<UserModel> getAllUser(Map<String, Object> map) {
        return userDao.getAllUser(map);
    }

    public UserModel getUserByAccount(String account) {
        return userDao.getUserByAccount(account);
    }
}
