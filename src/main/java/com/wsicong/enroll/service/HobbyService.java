package com.wsicong.enroll.service;

import com.wsicong.enroll.model.Hobby;
import com.wsicong.enroll.util.PageDataResult;

public interface HobbyService {
    /**
     * 分页查询兴趣分类列表
     *
     * @param page
     * @param limit
     * @return
     */
    PageDataResult list(int page, int limit, Hobby hobby);

    /**
     * 新增兴趣分类
     *
     * @param hobby
     * @return
     */
    String add(Hobby hobby);

    /**
     * 删除兴趣分类
     *
     * @param id
     * @return
     */
    String delete(Integer id);
}
