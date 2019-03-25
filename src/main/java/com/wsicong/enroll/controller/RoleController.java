package com.wsicong.enroll.controller;
import com.wsicong.enroll.core.Result;
import com.wsicong.enroll.util.ResultUtil;
import com.wsicong.enroll.model.Role;
import com.wsicong.enroll.service.RoleService;
import com.wsicong.enroll.input.RoleInput;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
* Created by Wsicong on 2019/03/22.
*/
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
    * 增加
    * @param role
    * @return
    */
    @PostMapping("/add")
    @ResponseBody
    public Result add(Role role) {
        roleService.add(role);
        return ResultUtil.success();
    }

    /**
    * 删除
    * @param id
    * @return
    */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(Integer id) {
        roleService.deleteById(id);
        return ResultUtil.success();
    }

    /**
    * 修改
    * @param role
    * @return
    */
    @PostMapping("/update")
    @ResponseBody
    public Result update(Role role) {
        roleService.update(role);
        return ResultUtil.success();
    }

    /**
    * 通过id查找
    * @param id
    * @return
    */
    @PostMapping("/detail")
    @ResponseBody
    public Result detail(Integer id) {
        Role role = roleService.selectById(id);
        return ResultUtil.success(role);
    }

    /**
    * 分页查找
    * @param input 输入对象
    * @return
    */
}
