package com.wsicong.enroll.service;

import com.wsicong.enroll.dto.ChildGuardianDTO;
import com.wsicong.enroll.dto.EnrollRecordSearchDTO;
import com.wsicong.enroll.model.EnrollRecord;
import com.wsicong.enroll.util.PageDataResult;

import java.util.List;

public interface EnrollRecordService {
    /**
     * 分页查询报名记录列表
     *
     * @param page
     * @param limit
     * @return
     */
    PageDataResult list(int page, int limit, EnrollRecordSearchDTO enrollRecordSearch);

    /**
     * 设置报名记录信息（添加、修改）
     *
     * @param enrollRecord
     * @return
     */
    String setEnrollRecord(EnrollRecord enrollRecord);

    /**
     * 删除报名记录信息
     *
     * @param id
     * @return
     */
    String delete(Integer id);

    /**
     * 根据id查找报名记录信息
     *
     * @param id
     * @return
     */
    EnrollRecord getEnrollRecordById(Integer id);

    /**
     * 获取所有报名记录信息（未删除的）
     *
     * @return
     */
    List<EnrollRecord> getEnrollRecords();

    /**
     * 添加报名记录信息
     *
     * @param childGuardianDTO
     * @return
     */
    String addEnrollRecord(ChildGuardianDTO childGuardianDTO);

    /**
     * 修改用户缴费状态
     *
     * @param id
     * @param isPay
     * @return
     */
    String setPayStatus(Integer id, Integer isPay);


}
