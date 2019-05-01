package com.wsicong.enroll.dto;

public class EnrollRecordSearchDTO {
    private Integer page;
    private Integer limit;
    private Integer userId;
    private String childIdSearch;
    private String insertTimeStart;
    private String insertTimeEnd;

    //少儿姓名
    private String childName;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getChildIdSearch() {
        return childIdSearch;
    }

    public void setChildIdSearch(String childIdSearch) {
        this.childIdSearch = childIdSearch;
    }

    public String getInsertTimeStart() {
        return insertTimeStart;
    }

    public void setInsertTimeStart(String insertTimeStart) {
        this.insertTimeStart = insertTimeStart;
    }

    public String getInsertTimeEnd() {
        return insertTimeEnd;
    }

    public void setInsertTimeEnd(String insertTimeEnd) {
        this.insertTimeEnd = insertTimeEnd;
    }

    @Override
    public String toString() {
        return "EnrollRecordSearchDTO{" +
                "page=" + page +
                ", limit=" + limit +
                ", userId=" + userId +
                ", childIdSearch='" + childIdSearch + '\'' +
                ", insertTimeStart='" + insertTimeStart + '\'' +
                ", insertTimeEnd='" + insertTimeEnd + '\'' +
                ", childName='" + childName + '\'' +
                '}';
    }
}
