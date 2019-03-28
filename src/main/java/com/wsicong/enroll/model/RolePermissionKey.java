package com.wsicong.enroll.model;

public class RolePermissionKey {
    /**
     *
     */
    private Integer permitId;

    /**
     *
     */
    private Integer roleId;

    public Integer getPermitId() {
        return permitId;
    }

    public void setPermitId(Integer permitId) {
        this.permitId = permitId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", permitId=").append(permitId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}