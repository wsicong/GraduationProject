package com.wsicong.enroll.dto;

public class GuardianDTO {
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
        this.guardianName = guardianName;
    }

    public String getGuardianSex() {
        return guardianSex;
    }

    public void setGuardianSex(String guardianSex) {
        this.guardianSex = guardianSex;
    }

    public String getGuardianIdCard() {
        return guardianIdCard;
    }

    public void setGuardianIdCard(String guardianIdCard) {
        this.guardianIdCard = guardianIdCard;
    }

    public String getGuardianRelation() {
        return guardianRelation;
    }

    public void setGuardianRelation(String guardianRelation) {
        this.guardianRelation = guardianRelation;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
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
        return "GuardianDTO{" +
                "id=" + id +
                ", childId=" + childId +
                ", guardianName='" + guardianName + '\'' +
                ", guardianSex='" + guardianSex + '\'' +
                ", guardianIdCard='" + guardianIdCard + '\'' +
                ", guardianRelation='" + guardianRelation + '\'' +
                ", guardianPhone='" + guardianPhone + '\'' +
                ", isDel=" + isDel +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
