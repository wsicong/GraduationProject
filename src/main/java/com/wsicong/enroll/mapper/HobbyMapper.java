package com.wsicong.enroll.mapper;

import com.wsicong.enroll.model.Hobby;

import java.util.List;

public interface HobbyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hobby record);

    int insertSelective(Hobby record);

    Hobby selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hobby record);

    int updateByPrimaryKey(Hobby record);

    List<Hobby> selectHobbyList(Hobby hobby);
}