package com.wsicong.enroll.mapper;

import com.wsicong.enroll.dto.EnrollRecordDTO;
import com.wsicong.enroll.dto.EnrollRecordSearchDTO;
import com.wsicong.enroll.model.EnrollRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnrollRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnrollRecord record);

    int insertSelective(EnrollRecord record);

    EnrollRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnrollRecord record);

    int updateByPrimaryKey(EnrollRecord record);

    /**
     * 分页查询报名记录信息
     *
     * @param enrollRecordSearch
     * @return
     */
    List<EnrollRecord> selectEnrollRecordList(@Param("enrollRecordSearch") EnrollRecordSearchDTO enrollRecordSearch);

    /**
     * 根据儿童id，班级id查找报名记录
     *
     * @param childId
     * @return
     */
    EnrollRecord selectByChildIdAndClassId(@Param("childId") Integer childId, @Param("classId") Integer classId);

    /**
     * 获取所有报名记录信息（未删除的）
     *
     * @return
     */
    List<EnrollRecord> selectEnrollRecords();
}