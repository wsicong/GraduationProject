package com.wsicong.enroll.service;

import com.wsicong.enroll.dto.HobbyClassSearchDTO;
import com.wsicong.enroll.model.HobbyClass;
import com.wsicong.enroll.util.PageDataResult;

import java.util.List;

public interface HobbyClassService {

    /**
     * 查询所有兴趣班
     *
     * @param hobbyClassSearchDTO
     * @return
     */
    List<HobbyClass> listHobbyClass(HobbyClassSearchDTO hobbyClassSearchDTO);

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

    /**
     * 设置班级状态
     *
     * @return
     */
    String setStatus();

    /**
     * 分页查询班级列表（status为1，is_enable为1）
     *
     * @param page
     * @param limit
     * @param hobbyClassSearch
     * @return
     */
    PageDataResult listUserClass(int page, int limit, HobbyClassSearchDTO hobbyClassSearch);
}
