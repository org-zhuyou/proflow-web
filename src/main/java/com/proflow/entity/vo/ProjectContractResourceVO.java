package com.proflow.entity.vo;

import com.proflow.entity.ProjectContractResource;
import com.proflow.entity.ResourceAttachment;

/**
 * Created by Leonid on 2018/9/3.
 */
public class ProjectContractResourceVO extends ProjectContractResource {

    private ResourceAttachment resourceAttachment;

    public ResourceAttachment getResourceAttachment() {
        return resourceAttachment;
    }

    public void setResourceAttachment(ResourceAttachment resourceAttachment) {
        this.resourceAttachment = resourceAttachment;
    }
}
