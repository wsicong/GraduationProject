package com.wsicong.enroll.vo;

import com.wsicong.enroll.model.HobbyType;

import java.util.Date;
import java.util.List;

public class HobbyVO {
    /**
     * 主键,自增
     */
    private Integer id;

    /**
     * 兴趣名称
     */
    private String hobbyName;

    /**
     * 兴趣类别详情
     */
    private String hobbyInfo;

    /**
     * 是否启用（1:启用，0：禁用）
     */
    private boolean enable;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 兴趣子类list
     */
    private List<HobbyType> hobbyTypes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public String getHobbyInfo() {
        return hobbyInfo;
    }

    public void setHobbyInfo(String hobbyInfo) {
        this.hobbyInfo = hobbyInfo;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<HobbyType> getHobbyTypes() {
        return hobbyTypes;
    }

    public void setHobbyTypes(List<HobbyType> hobbyTypes) {
        this.hobbyTypes = hobbyTypes;
    }

    @Override
    public String toString() {
        return "HobbyVO{" +
                "id=" + id +
                ", hobbyName='" + hobbyName + '\'' +
                ", hobbyInfo='" + hobbyInfo + '\'' +
                ", enable=" + enable +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", hobbyTypes=" + hobbyTypes +
                '}';
    }
}
