package com.wsicong.enroll.mapper;

import com.wsicong.enroll.model.UserDomain;

import java.util.List;

public interface UserMapper {

    int insert(UserDomain record);

    List<UserDomain> selectUsers();
}
