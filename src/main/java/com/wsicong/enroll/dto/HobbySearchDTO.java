package com.wsicong.enroll.dto;

public class HobbySearchDTO {
    private String hobbyNameSearch;
    private boolean enableSearch;

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

    @Override
    public String toString() {
        return "HobbySearchDTO{" +
                "hobbyNameSearch='" + hobbyNameSearch + '\'' +
                ", enableSearch=" + enableSearch +
                '}';
    }
}
