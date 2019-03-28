package com.wsicong.enroll.vo;

import com.wsicong.enroll.model.RolePermissionKey;

import java.util.List;

public class RoleVO {
    private Integer id;

    private String roleName;

    private String descpt;

    private String code;

    private Integer insertUid;

    private String insertTime;
    //角色下的权限ids
    private List<RolePermissionKey> rolePerms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescpt() {
        return descpt;
    }

    public void setDescpt(String descpt) {
        this.descpt = descpt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getInsertUid() {
        return insertUid;
    }

    public void setInsertUid(Integer insertUid) {
        this.insertUid = insertUid;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public List<RolePermissionKey> getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(List<RolePermissionKey> rolePerms) {
        this.rolePerms = rolePerms;
    }

    @Override
    public String toString() {
        return "RoleVO{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", descpt='" + descpt + '\'' +
                ", code='" + code + '\'' +
                ", insertUid=" + insertUid +
                ", insertTime='" + insertTime + '\'' +
                ", rolePerms=" + rolePerms +
                '}';
    }
}
