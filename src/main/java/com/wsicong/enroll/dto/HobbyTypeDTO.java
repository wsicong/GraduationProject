package com.wsicong.enroll.dto;

import java.util.Date;

public class HobbyTypeDTO {
    /**
     * 兴趣子类ID，主键
     */
    private Integer id;

    /**
     * 所属兴趣id
     */
    private Integer hobbyId;

    /**
     * 所属兴趣名称
     */
    private String hobbyName;

    /**
     * 兴趣名称
     */
    private String hobbyTypeName;

    /**
     * 兴趣学习内容
     */
    private String hobbyTypeInfo;

    /**
     * 学习用品说明
     */
    private String studyMaterials;

    /**
     * 是否启用（1:启用，0：禁用）
     */
    private boolean isEnable;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 备注
     */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Integer hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getHobbyTypeName() {
        return hobbyTypeName;
    }

    public void setHobbyTypeName(String hobbyTypeName) {
        this.hobbyTypeName = hobbyTypeName;
    }

    public String getHobbyTypeInfo() {
        return hobbyTypeInfo;
    }

    public void setHobbyTypeInfo(String hobbyTypeInfo) {
        this.hobbyTypeInfo = hobbyTypeInfo;
    }

    public String getStudyMaterials() {
        return studyMaterials;
    }

    public void setStudyMaterials(String studyMaterials) {
        this.studyMaterials = studyMaterials;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "HobbyTypeDTO{" +
                "id=" + id +
                ", hobbyId=" + hobbyId +
                ", hobbyName='" + hobbyName + '\'' +
                ", hobbyTypeName='" + hobbyTypeName + '\'' +
                ", hobbyTypeInfo='" + hobbyTypeInfo + '\'' +
                ", studyMaterials='" + studyMaterials + '\'' +
                ", isEnable=" + isEnable +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
