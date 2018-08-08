package com.proflow.entity.vo;

import com.proflow.entity.Menu;
import com.proflow.entity.Role;
import com.proflow.entity.User;

import java.util.List;

/**
 * Created by Leonid on 2018/8/6.
 */
public class UserVO extends User {

    private List<Role> roles;

    private List<Menu> menus;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
