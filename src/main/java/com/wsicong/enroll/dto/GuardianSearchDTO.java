package com.wsicong.enroll.dto;

public class GuardianSearchDTO {
    private Integer page;
    private Integer limit;
    private String guardianNameSearch;
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

    public String getGuardianNameSearch() {
        return guardianNameSearch;
    }

    public void setGuardianNameSearch(String guardianNameSearch) {
        this.guardianNameSearch = guardianNameSearch;
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
        return "GuardianSearchDTO{" +
                "page=" + page +
                ", limit=" + limit +
                ", guardianNameSearch='" + guardianNameSearch + '\'' +
                ", insertTimeStart='" + insertTimeStart + '\'' +
                ", insertTimeEnd='" + insertTimeEnd + '\'' +
                '}';
    }
}
