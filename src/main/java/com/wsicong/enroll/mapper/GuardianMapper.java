package com.wsicong.enroll.mapper;

import com.wsicong.enroll.dto.GuardianSearchDTO;
import com.wsicong.enroll.model.Guardian;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuardianMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Guardian record);

    int insertSelective(Guardian record);

    Guardian selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Guardian record);

    int updateByPrimaryKey(Guardian record);

    /**
     * 分页查询监护人信息
     *
     * @param guardianSearch
     * @return
     */
    List<Guardian> selectGuardianList(@Param("guardianSearch") GuardianSearchDTO guardianSearch);

    /**
     * 根据而监护人姓名查找监护人信息
     *
     * @param guardianName
     * @return
     */
    Guardian selectByGuardianName(String guardianName);

    /**
     * 获取所有监护人信息（未删除的）
     *
     * @return
     */
    List<Guardian> selectGuardian();
}