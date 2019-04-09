package com.wsicong.enroll.model;

import java.util.Date;

public class Child {
    /**
     * 少儿信息表ID
     */
    private Integer id;

    /**
     * 姓名
     */
    private String childName;

    /**
     * 性别
     */
    private String childSex;

    /**
     * 出生日期
     */
    private Date childBirth;

    /**
     * 年龄
     */
    private String childAge;

    /**
     * 住址
     */
    private String childAdress;

    /**
     * 籍贯
     */
    private String childRegion;

    /**
     * 民族
     */
    private String childNation;

    /**
     * 身份证
     */
    private String childIdCard;

    /**
     * 过敏食物或药物
     */
    private String childAllergies;

    /**
     * 重大病史
     */
    private String childMedicalHistory;

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

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName == null ? null : childName.trim();
    }

    public String getChildSex() {
        return childSex;
    }

    public void setChildSex(String childSex) {
        this.childSex = childSex == null ? null : childSex.trim();
    }

    public Date getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(Date childBirth) {
        this.childBirth = childBirth;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge == null ? null : childAge.trim();
    }

    public String getChildAdress() {
        return childAdress;
    }

    public void setChildAdress(String childAdress) {
        this.childAdress = childAdress == null ? null : childAdress.trim();
    }

    public String getChildRegion() {
        return childRegion;
    }

    public void setChildRegion(String childRegion) {
        this.childRegion = childRegion == null ? null : childRegion.trim();
    }

    public String getChildNation() {
        return childNation;
    }

    public void setChildNation(String childNation) {
        this.childNation = childNation == null ? null : childNation.trim();
    }

    public String getChildIdCard() {
        return childIdCard;
    }

    public void setChildIdCard(String childIdCard) {
        this.childIdCard = childIdCard == null ? null : childIdCard.trim();
    }

    public String getChildAllergies() {
        return childAllergies;
    }

    public void setChildAllergies(String childAllergies) {
        this.childAllergies = childAllergies == null ? null : childAllergies.trim();
    }

    public String getChildMedicalHistory() {
        return childMedicalHistory;
    }

    public void setChildMedicalHistory(String childMedicalHistory) {
        this.childMedicalHistory = childMedicalHistory == null ? null : childMedicalHistory.trim();
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