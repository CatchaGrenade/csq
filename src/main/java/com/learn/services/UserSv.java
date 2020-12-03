package com.learn.services;

import com.learn.base.service.BaseService;
import com.learn.base.service.impl.BaseServiceImpl;
import com.learn.model.UserModel;

import java.util.List;
import java.util.Map;

public interface UserSv extends BaseService<UserModel> {
    List<UserModel> getAllUser(Map<String, Object> map);

    UserModel getUserByAccount(String account);

}
