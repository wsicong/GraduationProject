package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.mapper.UserMapper;
import com.wsicong.enroll.model.UserDomain;
import com.wsicong.enroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public int addUser(UserDomain user) {
        return userMapper.insert(user);
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserDomain> userDomains= userMapper.selectUsers();
        PageInfo result=new PageInfo(userDomains);
        return result;
    }
}
