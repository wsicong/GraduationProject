package com.wsicong.enroll.mapper;

import com.wsicong.enroll.dto.HobbyClassSearchDTO;
import com.wsicong.enroll.model.HobbyClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HobbyClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HobbyClass record);

    int insertSelective(HobbyClass record);

    HobbyClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HobbyClass record);

    int updateByPrimaryKey(HobbyClass record);

    /**
     * 分页查询兴趣班列表
     *
     * @param hobbyClassSearch
     * @return
     */
    List<HobbyClass> selectHobbyClassList(@Param("hobbyClassSearch") HobbyClassSearchDTO hobbyClassSearch);

    /**
     * 根据兴趣班级名称查找
     *
     * @param className
     * @return
     */
    HobbyClass selectByHobbyClassName(String className);

    /**
     * 更新兴趣班级启用状态
     *
     * @param id
     * @param isEnable
     * @return
     */
    int updateEnable(@Param("id") Integer id, @Param("isEnable") Integer isEnable);

    /**
     * 更新班级状态 （1：正在招生，2：正在授课，3：课程结束）
     *
     * @return
     */
    int updateEnrollStatus();

    int updateStartStatus();

    int updateEndStatus();
}