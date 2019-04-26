package com.proflow.entity.vo;

import com.proflow.entity.Project;

/**
 * Created by Leonid on 2019/4/25.
 */
public class ProjectVO extends Project {

    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
