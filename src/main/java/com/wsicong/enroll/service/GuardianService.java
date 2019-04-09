package com.wsicong.enroll.service;

import com.wsicong.enroll.dto.GuardianSearchDTO;
import com.wsicong.enroll.model.Guardian;
import com.wsicong.enroll.util.PageDataResult;

import java.util.List;

public interface GuardianService {
    /**
     * 分页查询监护人列表
     *
     * @param page
     * @param limit
     * @return
     */
    PageDataResult list(int page, int limit, GuardianSearchDTO guardianSearch);

    /**
     * 设置监护人信息（添加、修改）
     *
     * @param guardian
     * @return
     */
    String setGuardian(Guardian guardian);

    /**
     * 删除监护人信息
     *
     * @param id
     * @return
     */
    String delete(Integer id);

    /**
     * 根据id查找监护人信息
     *
     * @param id
     * @return
     */
    Guardian getGuardianById(Integer id);

    /**
     * 获取所有监护人信息（未删除的）
     *
     * @return
     */
    List<Guardian> getGuardians();
}
