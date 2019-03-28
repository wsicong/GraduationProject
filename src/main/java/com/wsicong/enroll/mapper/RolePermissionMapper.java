package com.wsicong.enroll.mapper;

import com.wsicong.enroll.model.RolePermissionKey;

import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(RolePermissionKey key);

    int insert(RolePermissionKey record);

    int insertSelective(RolePermissionKey record);

    List<RolePermissionKey> findByRole(int roleId);
}