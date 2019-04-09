package com.wsicong.enroll.dto;

public class EnrollRecordSearchDTO {
    private Integer page;
    private Integer limit;
    private String childIdSearch;
    private String insertTimeStart;
    private String insertTimeEnd;

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
                ", childIdSearch='" + childIdSearch + '\'' +
                ", insertTimeStart='" + insertTimeStart + '\'' +
                ", insertTimeEnd='" + insertTimeEnd + '\'' +
                '}';
    }
}
