package com.proflow.entity.vo;

import com.proflow.entity.ProjectPhase;
import com.proflow.entity.ProjectPhaseAttachment;
import com.proflow.entity.ResourceAttachment;

/**
 * Created by Leonid on 2018/9/15.
 */
public class ProjectPhaseAttrVO extends ProjectPhaseAttachment {

    private ProjectPhase projectPhase;

    private ResourceAttachment resourceAttachment;

    public ResourceAttachment getResourceAttachment() {
        return resourceAttachment;
    }

    public void setResourceAttachment(ResourceAttachment resourceAttachment) {
        this.resourceAttachment = resourceAttachment;
    }

    public ProjectPhase getProjectPhase() {
        return projectPhase;
    }

    public void setProjectPhase(ProjectPhase projectPhase) {
        this.projectPhase = projectPhase;
    }
}
