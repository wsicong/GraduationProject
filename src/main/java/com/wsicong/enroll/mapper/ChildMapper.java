package com.wsicong.enroll.mapper;

import com.wsicong.enroll.dto.ChildSearchDTO;
import com.wsicong.enroll.model.Child;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChildMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Child record);

    int insertSelective(Child record);

    Child selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Child record);

    int updateByPrimaryKey(Child record);

    /**
     * 分页查询儿童信息
     *
     * @param childSearch
     * @return
     */
    List<Child> selectChildList(@Param("childSearch") ChildSearchDTO childSearch);

    /**
     * 根据而儿童姓名查找儿童信息
     *
     * @param childName
     * @return
     */
    Child selectByChildName(String childName);

    /**
     * 获取所有儿童信息（未删除的）
     *
     * @return
     */
    List<Child> selectChild();
}