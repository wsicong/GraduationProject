package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.mapper.RoleMapper;
import com.wsicong.enroll.input.RoleInput;
import com.wsicong.enroll.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import com.wsicong.enroll.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


/**
* Created by Wsicong on 2019/03/22.
*/
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public int add(Role role) {
        return roleMapper.insert(role);
    }

    @Transactional
    @Override
    public int deleteById(Integer roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Transactional
    @Override
    public int update(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public Role selectById(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

}
