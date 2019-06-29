package com.ryml.entity;

import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/29
 */
public class Menu {

    private Integer id;

    private String name;

    private Integer parentId;

    private List<Menu> childrenMenus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildrenMenus() {
        return childrenMenus;
    }

    public void setChildrenMenus(List<Menu> childrenMenus) {
        this.childrenMenus = childrenMenus;
    }
}
