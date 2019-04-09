package com.wsicong.enroll.service;

import com.wsicong.enroll.dto.HobbySearchDTO;
import com.wsicong.enroll.model.Hobby;
import com.wsicong.enroll.util.PageDataResult;

import java.util.List;

public interface HobbyService {
    /**
     * 分页查询兴趣列表
     *
     * @param page
     * @param limit
     * @return
     */
    PageDataResult list(int page, int limit, HobbySearchDTO hobbySearch);

    /**
     * 设置兴趣（添加、修改）
     *
     * @param hobby
     * @return
     */
    String setHobby(Hobby hobby);

    /**
     * 删除兴趣
     *
     * @param id
     * @return
     */
    String delete(Integer id);

    /**
     * 根据id查找兴趣
     *
     * @param id
     * @return
     */
    Hobby getHobby(Integer id);

    /**
     * 设置是否启用兴趣
     *
     * @param id
     * @param isEnable
     * @return
     */
    String setEnable(Integer id, Integer isEnable);

    /**
     * 获取所有兴趣（启用的）
     *
     * @return
     */
    List<Hobby> getHobbies();
}
