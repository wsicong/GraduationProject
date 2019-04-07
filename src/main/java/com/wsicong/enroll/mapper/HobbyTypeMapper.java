package com.wsicong.enroll.mapper;

import com.wsicong.enroll.dto.HobbyTypeSearchDTO;
import com.wsicong.enroll.model.HobbyType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HobbyTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HobbyType record);

    int insertSelective(HobbyType record);

    HobbyType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HobbyType record);

    int updateByPrimaryKey(HobbyType record);

    /**
     * 分页查询兴趣分类列表
     *
     * @param hobbyTypeSearch
     * @return
     */
    List<HobbyType> selectHobbyTypeList(@Param("hobbyTypeSearch") HobbyTypeSearchDTO hobbyTypeSearch);

    /**
     * 根据兴趣分类名称查找兴趣分类
     *
     * @param hobbyTypeName
     * @return
     */
    HobbyType selectByHobbyTypeName(String hobbyTypeName);

    /**
     * 更新兴趣分类启用状态
     *
     * @param id
     * @param isEnable
     * @return
     */
    int updateEnable(@Param("id") Integer id, @Param("isEnable") Integer isEnable);
}