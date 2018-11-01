package com.proflow.entity.vo;

import com.proflow.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leonid on 2018/10/31.
 */
public class MenuVO extends Menu {

    private List<Menu> children = new ArrayList<>();

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

}
