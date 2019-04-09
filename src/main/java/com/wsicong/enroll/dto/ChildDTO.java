package com.wsicong.enroll.dto;

public class ChildDTO {
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
    private String childBirth;

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

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildSex() {
        return childSex;
    }

    public void setChildSex(String childSex) {
        this.childSex = childSex;
    }

    public String getChildBirth() {
        return childBirth;
    }

    public void setChildBirth(String childBirth) {
        this.childBirth = childBirth;
    }

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public String getChildAdress() {
        return childAdress;
    }

    public void setChildAdress(String childAdress) {
        this.childAdress = childAdress;
    }

    public String getChildRegion() {
        return childRegion;
    }

    public void setChildRegion(String childRegion) {
        this.childRegion = childRegion;
    }

    public String getChildNation() {
        return childNation;
    }

    public void setChildNation(String childNation) {
        this.childNation = childNation;
    }

    public String getChildIdCard() {
        return childIdCard;
    }

    public void setChildIdCard(String childIdCard) {
        this.childIdCard = childIdCard;
    }

    public String getChildAllergies() {
        return childAllergies;
    }

    public void setChildAllergies(String childAllergies) {
        this.childAllergies = childAllergies;
    }

    public String getChildMedicalHistory() {
        return childMedicalHistory;
    }

    public void setChildMedicalHistory(String childMedicalHistory) {
        this.childMedicalHistory = childMedicalHistory;
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
        return "ChildDTO{" +
                "id=" + id +
                ", childName='" + childName + '\'' +
                ", childSex='" + childSex + '\'' +
                ", childBirth='" + childBirth + '\'' +
                ", childAge='" + childAge + '\'' +
                ", childAdress='" + childAdress + '\'' +
                ", childRegion='" + childRegion + '\'' +
                ", childNation='" + childNation + '\'' +
                ", childIdCard='" + childIdCard + '\'' +
                ", childAllergies='" + childAllergies + '\'' +
                ", childMedicalHistory='" + childMedicalHistory + '\'' +
                ", isDel=" + isDel +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
