package com.proflow.entity.vo;

import com.proflow.entity.ProjectPhaseAttachment;
import com.proflow.entity.ResourceAttachment;

/**
 * Created by Leonid on 2018/9/15.
 */
public class ProjectPhaseAttrVO extends ProjectPhaseAttachment {

    private ResourceAttachment resourceAttachment;

    public ResourceAttachment getResourceAttachment() {
        return resourceAttachment;
    }

    public void setResourceAttachment(ResourceAttachment resourceAttachment) {
        this.resourceAttachment = resourceAttachment;
    }

}
