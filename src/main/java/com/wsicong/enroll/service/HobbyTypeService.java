package com.wsicong.enroll.service;

import com.wsicong.enroll.dto.HobbyTypeSearchDTO;
import com.wsicong.enroll.model.HobbyType;
import com.wsicong.enroll.util.PageDataResult;

public interface HobbyTypeService {
    /**
     * 分页查询兴趣分类列表
     *
     * @param page
     * @param limit
     * @return
     */
    PageDataResult list(int page, int limit, HobbyTypeSearchDTO hobbyTypeSearch);

    /**
     * 设置兴趣分类（新增、修改）
     *
     * @param hobbyType
     * @return
     */
    String setHobbyType(HobbyType hobbyType);

    /**
     * 删除兴趣分类
     *
     * @param id
     * @return
     */
    String delete(Integer id);

    /**
     * 根据id查找兴趣分类
     *
     * @param id
     * @return
     */
    HobbyType getHobbyType(Integer id);

    /**
     * 设置是否启用兴趣分类
     *
     * @param id
     * @param isEnable
     * @return
     */
    String setEnable(Integer id, Integer isEnable);
}
