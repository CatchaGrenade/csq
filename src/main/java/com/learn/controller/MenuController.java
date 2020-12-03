package com.learn.controller;

import com.learn.model.MenuModel;
import com.learn.protocol.response.ApiResponse;
import com.learn.services.MenuSv;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Resource
    MenuSv menuSv;

    @RequestMapping("/getAllMenu")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<MenuModel>>> getAllMenu() {
        List<MenuModel> list = menuSv.getAllMenu();
        List<MenuModel> firstLeverList = new LinkedList<MenuModel>();
        // 菜单结构树处理
        for (MenuModel map : list) {
            String parentId = String.valueOf(map.getParentId());
            if (null!=parentId && "0".equals(parentId)) {
                firstLeverList.add(map);
            }
        }
        list.removeAll(firstLeverList);
        for (MenuModel map : firstLeverList) {
            String menuId = String.valueOf(map.getMenuId());
            List<MenuModel> secondLeverList = new LinkedList<MenuModel>();
            for (MenuModel tempMap : list) {
                String parentId = String.valueOf(tempMap.getParentId());
                if (parentId.equals(menuId)) {
                    secondLeverList.add(tempMap);
                }
            }
            map.setChild(secondLeverList);
            list.removeAll(secondLeverList);
        }
        return ResponseEntity.ok(new ApiResponse<List<MenuModel>>(firstLeverList));
    }
}
