package com.proflow.web.form;

import com.proflow.entity.User;

import java.util.List;

/**
 * Created by Leonid on 2018/8/8.
 */
public class UserForm extends User {

    private List<Long> roles;

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
}
