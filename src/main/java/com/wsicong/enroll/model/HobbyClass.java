package com.wsicong.enroll.model;

import java.util.Date;

/**
 * 兴趣班POJO
 */
public class HobbyClass {
    /**
     * 班级id
     */
    private Integer id;

    /**
     * 所属兴趣id
     */
    private Integer hobbyTypeId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 招生对象说明
     */
    private String studentDescribe;

    /**
     * 招生对象年龄
     */
    private String studentAge;

    /**
     * 招生人数
     */
    private String enrollNum;

    /**
     * 上课开始日期（年月日）
     */
    private Date classStartDate;

    /**
     * 上课结束日期（年月日）
     */
    private Date classEndDate;

    /**
     * 上课星期（周一到周五）
     */
    private String classWeeks;

    /**
     * 上课开始时间（时分）
     */
    private Date classStartTime;

    /**
     * 上课结束时间（时分）
     */
    private Date classEndTime;

    /**
     * 收费
     */
    private String classCost;

    /**
     * 课时
     */
    private String classHour;

    /**
     * 状态（1：正在报名，2：报名结束，3：正在授课，4：结束授课）
     */
    private Integer status;

    /**
     * 是否启用（1:启用，0：禁用）
     */
    private Boolean enable;

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

    public Integer getHobbyTypeId() {
        return hobbyTypeId;
    }

    public void setHobbyTypeId(Integer hobbyTypeId) {
        this.hobbyTypeId = hobbyTypeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentDescribe() {
        return studentDescribe;
    }

    public void setStudentDescribe(String studentDescribe) {
        this.studentDescribe = studentDescribe;
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    public String getEnrollNum() {
        return enrollNum;
    }

    public void setEnrollNum(String enrollNum) {
        this.enrollNum = enrollNum;
    }

    public Date getClassStartDate() {
        return classStartDate;
    }

    public void setClassStartDate(Date classStartDate) {
        this.classStartDate = classStartDate;
    }

    public Date getClassEndDate() {
        return classEndDate;
    }

    public void setClassEndDate(Date classEndDate) {
        this.classEndDate = classEndDate;
    }

    public String getClassWeeks() {
        return classWeeks;
    }

    public void setClassWeeks(String classWeeks) {
        this.classWeeks = classWeeks;
    }

    public Date getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(Date classStartTime) {
        this.classStartTime = classStartTime;
    }

    public Date getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(Date classEndTime) {
        this.classEndTime = classEndTime;
    }

    public String getClassCost() {
        return classCost;
    }

    public void setClassCost(String classCost) {
        this.classCost = classCost;
    }

    public String getClassHour() {
        return classHour;
    }

    public void setClassHour(String classHour) {
        this.classHour = classHour;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
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

    @Override
    public String toString() {
        return "HobbyClass{" +
                "id=" + id +
                ", hobbyTypeId=" + hobbyTypeId +
                ", className='" + className + '\'' +
                ", studentDescribe='" + studentDescribe + '\'' +
                ", studentAge='" + studentAge + '\'' +
                ", enrollNum='" + enrollNum + '\'' +
                ", classStartDate=" + classStartDate +
                ", classEndDate=" + classEndDate +
                ", classWeeks='" + classWeeks + '\'' +
                ", classStartTime=" + classStartTime +
                ", classEndTime=" + classEndTime +
                ", classCost='" + classCost + '\'' +
                ", classHour='" + classHour + '\'' +
                ", status=" + status +
                ", enable=" + enable +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}