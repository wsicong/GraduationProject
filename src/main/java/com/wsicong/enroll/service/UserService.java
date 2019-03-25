package com.wsicong.enroll.service;

import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.model.UserDomain;

public interface UserService {
    int addUser(UserDomain user);

    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
