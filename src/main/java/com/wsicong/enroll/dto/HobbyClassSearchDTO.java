package com.wsicong.enroll.dto;

public class HobbyClassSearchDTO {
    private Integer page;
    private Integer limit;
    private String classNameSearch;
    private boolean enableSearch;
    private String insertTimeStart;
    private String insertTimeEnd;

    //兴趣分类
    private Integer hobbyTypeId;

    //班级是否启用
    private boolean isEnable;

    //班级状态
    private Integer status;

    public Integer getHobbyTypeId() {
        return hobbyTypeId;
    }

    public void setHobbyTypeId(Integer hobbyTypeId) {
        this.hobbyTypeId = hobbyTypeId;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getClassNameSearch() {
        return classNameSearch;
    }

    public void setClassNameSearch(String classNameSearch) {
        this.classNameSearch = classNameSearch;
    }

    public boolean isEnableSearch() {
        return enableSearch;
    }

    public void setEnableSearch(boolean enableSearch) {
        this.enableSearch = enableSearch;
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
        return "HobbyClassSearchDTO{" +
                "page=" + page +
                ", limit=" + limit +
                ", classNameSearch='" + classNameSearch + '\'' +
                ", enableSearch=" + enableSearch +
                ", insertTimeStart='" + insertTimeStart + '\'' +
                ", insertTimeEnd='" + insertTimeEnd + '\'' +
                ", hobbyTypeId=" + hobbyTypeId +
                ", isEnable=" + isEnable +
                ", status=" + status +
                '}';
    }
}
