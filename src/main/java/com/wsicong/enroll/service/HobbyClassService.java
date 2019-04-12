package com.wsicong.enroll.service;

import com.wsicong.enroll.dto.HobbyClassSearchDTO;
import com.wsicong.enroll.model.HobbyClass;
import com.wsicong.enroll.util.PageDataResult;

public interface HobbyClassService {
    /**
     * 分页查询兴趣班级列表
     *
     * @param page
     * @param limit
     * @return
     */
    PageDataResult list(int page, int limit, HobbyClassSearchDTO hobbyClassSearch);

    /**
     * 设置兴趣班级（新增、修改）
     *
     * @param hobbyClass
     * @return
     */
    String setHobbyClass(HobbyClass hobbyClass);

    /**
     * 删除兴趣班级
     *
     * @param id
     * @return
     */
    String delete(Integer id);

    /**
     * 根据id查找兴趣班级
     *
     * @param id
     * @return
     */
    HobbyClass getHobbyClass(Integer id);

    /**
     * 设置是否启用兴趣班级
     *
     * @param id
     * @param isEnable
     * @return
     */
    String setEnable(Integer id, Integer isEnable);


}
