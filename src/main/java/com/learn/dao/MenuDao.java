package com.learn.dao;

import com.learn.model.MenuModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    List<MenuModel> getAllMenu();
}
