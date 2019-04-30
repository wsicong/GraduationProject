package com.wsicong.enroll.dto;

/**
 * 报名表单页面DTO
 */
public class ChildGuardianDTO {
    /**
     * 兴趣班级id
     */
    private Integer hobbyClassId;

    /**
     * 兴趣班级名称
     */
    private String hobbyClassName;

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
    private String childBirthStr;

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

    public Integer getHobbyClassId() {
        return hobbyClassId;
    }

    public void setHobbyClassId(Integer hobbyClassId) {
        this.hobbyClassId = hobbyClassId;
    }

    public String getHobbyClassName() {
        return hobbyClassName;
    }

    public void setHobbyClassName(String hobbyClassName) {
        this.hobbyClassName = hobbyClassName;
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

    public String getChildBirthStr() {
        return childBirthStr;
    }

    public void setChildBirthStr(String childBirthStr) {
        this.childBirthStr = childBirthStr;
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

    @Override
    public String toString() {
        return "ChildGuardianDTO{" +
                "hobbyClassId=" + hobbyClassId +
                ", hobbyClassName='" + hobbyClassName + '\'' +
                ", childName='" + childName + '\'' +
                ", childSex='" + childSex + '\'' +
                ", childBirthStr='" + childBirthStr + '\'' +
                ", childAge='" + childAge + '\'' +
                ", childAdress='" + childAdress + '\'' +
                ", childRegion='" + childRegion + '\'' +
                ", childNation='" + childNation + '\'' +
                ", childIdCard='" + childIdCard + '\'' +
                ", childAllergies='" + childAllergies + '\'' +
                ", childMedicalHistory='" + childMedicalHistory + '\'' +
                ", guardianName='" + guardianName + '\'' +
                ", guardianSex='" + guardianSex + '\'' +
                ", guardianIdCard='" + guardianIdCard + '\'' +
                ", guardianRelation='" + guardianRelation + '\'' +
                ", guardianPhone='" + guardianPhone + '\'' +
                '}';
    }
}
