package com.wsicong.enroll.mapper;

import com.wsicong.enroll.dto.HobbySearchDTO;
import com.wsicong.enroll.model.Hobby;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HobbyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Hobby record);

    int insertSelective(Hobby record);

    Hobby selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Hobby record);

    int updateByPrimaryKey(Hobby record);

    /**
     * 分页查询兴趣列表
     *
     * @param hobbySearch
     * @return
     */
    List<Hobby> selectHobbyList(@Param("hobbySearch") HobbySearchDTO hobbySearch);

    /**
     * 根据兴趣名称查找兴趣
     *
     * @param hobbyName
     * @return
     */
    Hobby selectByHobbyName(String hobbyName);

    /**
     * 更新兴趣启用状态
     *
     * @param id
     * @param enable
     * @return
     */
    String updateEnable(Integer id, boolean enable);
}