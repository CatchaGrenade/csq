package com.learn.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuModel implements Serializable {
    private Integer menuId;
    private String menuName;
    private String linkUrl;
    private String target;
    private Integer parentId;
    private Integer sort;
    private String imageUrl;
    private String showType;
    private String isActive;
    private String remark;
    private List<MenuModel> child;
}
