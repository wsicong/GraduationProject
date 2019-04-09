package com.wsicong.enroll.dto;

public class ChildSearchDTO {
    private Integer page;
    private Integer limit;
    private String childNameSearch;
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

    public String getChildNameSearch() {
        return childNameSearch;
    }

    public void setChildNameSearch(String childNameSearch) {
        this.childNameSearch = childNameSearch;
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
        return "ChildSearchDTO{" +
                "page=" + page +
                ", limit=" + limit +
                ", childNameSearch='" + childNameSearch + '\'' +
                ", insertTimeStart='" + insertTimeStart + '\'' +
                ", insertTimeEnd='" + insertTimeEnd + '\'' +
                '}';
    }
}
