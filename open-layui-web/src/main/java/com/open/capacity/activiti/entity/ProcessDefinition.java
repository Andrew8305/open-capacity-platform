package com.open.capacity.activiti.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: [gitgeek]
 * @Date: [2018-05-14 19:23]
 * @Description: [ 流程定义实体 ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */
@Getter
@Setter
@ToString
public class ProcessDefinition {
    private String id;
    private String category;
    private String name;
    private String key;
    private String description;
    private int version;
    private String resourceName;
    private String deploymentId;
    private String diagramResourceName;
    private boolean hasStartFormKey;
    private boolean hasGraphicalNotation;
    private boolean isSuspended;
    private String tenantId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getDiagramResourceName() {
        return diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName) {
        this.diagramResourceName = diagramResourceName;
    }

    public boolean isHasStartFormKey() {
        return hasStartFormKey;
    }

    public void setHasStartFormKey(boolean hasStartFormKey) {
        this.hasStartFormKey = hasStartFormKey;
    }

    public boolean isHasGraphicalNotation() {
        return hasGraphicalNotation;
    }

    public void setHasGraphicalNotation(boolean hasGraphicalNotation) {
        this.hasGraphicalNotation = hasGraphicalNotation;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public ProcessDefinition() {
    }
    public ProcessDefinition(org.activiti.engine.repository.ProcessDefinition p) {
        this.id=p.getId();
        this.category=p.getCategory();
        this.name=p.getName();
        this.key=p.getKey();
        this.description=p.getDescription();
        this.version=p.getVersion();
        this.resourceName=p.getResourceName();
        this.deploymentId=p.getDeploymentId();
        this.diagramResourceName=p.getDiagramResourceName();
        this.hasStartFormKey=p.hasStartFormKey();
        this.hasGraphicalNotation=p.hasGraphicalNotation();
        this.isSuspended=p.isSuspended();
        this.tenantId=p.getTenantId();

    }
}
