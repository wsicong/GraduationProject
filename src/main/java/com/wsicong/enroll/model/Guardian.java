package com.wsicong.enroll.model;

import java.util.Date;

public class Guardian {
    /**
     * 监护人信息表ID
     */
    private Integer id;

    /**
     * 儿童ID
     */
    private Integer childId;

    /**
     * 姓名
     */
    private String guardianName;

    /**
     * 性别
     */
    private String guardianSex;

    /**
     * 身份证
     */
    private String guardianIdCard;

    /**
     * 与少儿的关系
     */
    private String guardianRelation;

    /**
     * 联系方式
     */
    private String guardianPhone;

    /**
     * 是否删除（0：正常，1：已删）
     */
    private Boolean isDel;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName == null ? null : guardianName.trim();
    }

    public String getGuardianSex() {
        return guardianSex;
    }

    public void setGuardianSex(String guardianSex) {
        this.guardianSex = guardianSex == null ? null : guardianSex.trim();
    }

    public String getGuardianIdCard() {
        return guardianIdCard;
    }

    public void setGuardianIdCard(String guardianIdCard) {
        this.guardianIdCard = guardianIdCard == null ? null : guardianIdCard.trim();
    }

    public String getGuardianRelation() {
        return guardianRelation;
    }

    public void setGuardianRelation(String guardianRelation) {
        this.guardianRelation = guardianRelation == null ? null : guardianRelation.trim();
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone == null ? null : guardianPhone.trim();
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.updateBy = updateBy == null ? null : updateBy.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }
}