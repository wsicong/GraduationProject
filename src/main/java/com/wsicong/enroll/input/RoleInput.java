package com.wsicong.enroll.input;

import com.wsicong.enroll.model.Role;
import javax.validation.constraints.NotNull;
/**
* Created by Wsicong on 2019/03/22.
*/
public class RoleInput extends Role {//输入对象

    @NotNull(message = "分页参数pageNum不能为空")
    private Integer pageNum=1;

    @NotNull(message = "分页参数pageSize不能为空")
    private Integer pageSize=10;

    private String keywords;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
