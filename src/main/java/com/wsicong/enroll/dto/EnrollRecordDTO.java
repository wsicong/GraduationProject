package com.wsicong.enroll.dto;

public class EnrollRecordDTO {
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
    private String enrollStartTime;

    /**
     * 报名结束时间
     */
    private String enrollEndTime;

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
        this.className = className;
    }

    public String getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(String enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public String getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(String enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public Boolean getPay() {
        return isPay;
    }

    public void setPay(Boolean pay) {
        isPay = pay;
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
        return "EnrollRecordDTO{" +
                "id=" + id +
                ", childId=" + childId +
                ", guardianId=" + guardianId +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", enrollStartTime='" + enrollStartTime + '\'' +
                ", enrollEndTime='" + enrollEndTime + '\'' +
                ", childName='" + childName + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", guardianPhone='" + guardianPhone + '\'' +
                ", isPay=" + isPay +
                ", isDel=" + isDel +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
