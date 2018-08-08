package com.proflow.web.form;

import com.proflow.entity.Role;

import java.util.List;

/**
 * Created by Leonid on 2018/8/8.
 */
public class RoleForm extends Role {

    private List<Long> menus;

    public List<Long> getMenus() {
        return menus;
    }

    public void setMenus(List<Long> menus) {
        this.menus = menus;
    }
}
