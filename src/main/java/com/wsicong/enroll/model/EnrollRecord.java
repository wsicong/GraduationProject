package com.wsicong.enroll.model;

import java.util.Date;

public class EnrollRecord {
    /**
     * 报名表ID
     */
    private Integer id;

    /**
     * 少儿信息ID
     */
    private Integer childId;

    /**
     * 监护人信息ID
     */
    private Integer guardianId;

    /**
     * 班级信息ID
     */
    private Integer classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 报名开始时间
     */
    private Date enrollStartTime;

    /**
     * 报名结束时间
     */
    private Date enrollEndTime;

    /**
     * 少儿姓名
     */
    private String childName;

    /**
     * 监护人姓名
     */
    private String guardianName;

    /**
     * 监护人联系方式
     */
    private String guardianPhone;

    /**
     * 是否缴费（1：是，0：否）
     */
    private Boolean isPay;

    /**
     * 是否删除（0：正常，1：已删）
     */
    private Boolean isDel;

    /**
     * 创建者（此处相当于报名者）
     */
    private String createBy;

    /**
     * 创建时间（此处相当于报名时间）
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

    public Integer getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(Integer guardianId) {
        this.guardianId = guardianId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Date getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(Date enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public Date getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(Date enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName == null ? null : childName.trim();
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName == null ? null : guardianName.trim();
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone == null ? null : guardianPhone.trim();
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
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

    @Override
    public String toString() {
        return "EnrollRecordDTO{" +
                "id=" + id +
                ", childId=" + childId +
                ", guardianId=" + guardianId +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", enrollStartTime=" + enrollStartTime +
                ", enrollEndTime=" + enrollEndTime +
                ", childName='" + childName + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", guardianPhone='" + guardianPhone + '\'' +
                ", isPay=" + isPay +
                ", isDel=" + isDel +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}