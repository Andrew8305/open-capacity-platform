package com.open.capacity.activiti.entity;


import lombok.Getter;
import lombok.Setter;
import org.activiti.engine.repository.Model;

import java.util.Date;

/**
 * @Author: [gitgeek]
 * @Date: [2018-05-14 19:19]
 * @Description: [ 模型列表 ]
 * @Version: [1.0.0]
 * @Copy: [com.zzg]
 */
@Getter
@Setter
public class ActModel {

    private String id;
    private String name;
    private String key;
    private String category;
    private Date createTime;
    private Date lastUpdateTime;
    private Integer version;
    private String metaInfo;
    private String deploymentId;
    private String tenantId;
    private boolean hasEditorSource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public boolean isHasEditorSource() {
        return hasEditorSource;
    }

    public void setHasEditorSource(boolean hasEditorSource) {
        this.hasEditorSource = hasEditorSource;
    }

    public ActModel() {
    }

    public ActModel(Model model) {
        this.id = model.getId();
        this.name = model.getName();
        this.key = model.getKey();
        this.category = model.getCategory();
        this.createTime = model.getCreateTime();
        this.lastUpdateTime = model.getLastUpdateTime();
        this.version = model.getVersion();
        this.metaInfo = model.getMetaInfo();
        this.deploymentId = model.getDeploymentId();
        this.tenantId = model.getTenantId();
        this.hasEditorSource = model.hasEditorSource();
    }
}
