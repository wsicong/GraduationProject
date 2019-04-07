package com.wsicong.enroll.dto;

public class HobbyTypeSearchDTO {
    private Integer page;
    private Integer limit;
    private String hobbyTypeNameSearch;
    private boolean enableSearch;
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

    public String getHobbyTypeNameSearch() {
        return hobbyTypeNameSearch;
    }

    public void setHobbyTypeNameSearch(String hobbyTypeNameSearch) {
        this.hobbyTypeNameSearch = hobbyTypeNameSearch;
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
        return "HobbyTypeSearchDTO{" +
                "page=" + page +
                ", limit=" + limit +
                ", hobbyTypeNameSearch='" + hobbyTypeNameSearch + '\'' +
                ", enableSearch=" + enableSearch +
                ", insertTimeStart='" + insertTimeStart + '\'' +
                ", insertTimeEnd='" + insertTimeEnd + '\'' +
                '}';
    }
}
