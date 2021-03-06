package com.wsicong.enroll.dto;

public class HobbySearchDTO {
    private Integer page;
    private Integer limit;
    private String hobbyNameSearch;
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

    public String getHobbyNameSearch() {
        return hobbyNameSearch;
    }

    public void setHobbyNameSearch(String hobbyNameSearch) {
        this.hobbyNameSearch = hobbyNameSearch;
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
        return "HobbySearchDTO{" +
                "page=" + page +
                ", limit=" + limit +
                ", hobbyNameSearch='" + hobbyNameSearch + '\'' +
                ", enableSearch=" + enableSearch +
                ", insertTimeStart='" + insertTimeStart + '\'' +
                ", insertTimeEnd='" + insertTimeEnd + '\'' +
                '}';
    }
}
