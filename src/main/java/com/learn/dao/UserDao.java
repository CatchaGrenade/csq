package com.learn.dao;

import com.learn.base.mapper.BaseMapper;
import com.learn.model.UserModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao extends BaseMapper<UserModel> {
    List<UserModel> getAllUser(Map<String, Object> map);

    UserModel getUserByAccount(@Param(value = "account") String account);
}
