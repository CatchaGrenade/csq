package com.learn.services.impl;

import com.learn.dao.MenuDao;
import com.learn.model.MenuModel;
import com.learn.services.MenuSv;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MenuSvImpl implements MenuSv {

    @Resource
    MenuDao menuDao;
    public List<MenuModel> getAllMenu() {
        return menuDao.getAllMenu();
    }
}
