package com.wsicong.enroll.service;
import com.wsicong.enroll.model.Role;
import com.wsicong.enroll.input.RoleInput;
import com.github.pagehelper.PageInfo;

/**
* Created by Wsicong on 2019/03/22.
*/
public interface RoleService {

    /**
    * 增加
    * @param role
    */
    int add(Role role);

    /**
    * 通过id删除
    * @param roleId
    */
    int deleteById(Integer roleId);

    /**
    * 修改
    * @param role
    */
    int update(Role role);

    /**
    * 通过id查找
    * @param roleId
    * @return
    */
    Role selectById(Integer roleId);

    /**
    * 通过输入参数分页查找
    * @param roleInput
    * @return
    */

}
