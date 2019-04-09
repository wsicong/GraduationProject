package com.wsicong.enroll.service;

import com.wsicong.enroll.dto.ChildSearchDTO;
import com.wsicong.enroll.model.Child;
import com.wsicong.enroll.util.PageDataResult;

import java.util.List;

public interface ChildService {
    /**
     * 分页查询儿童列表
     *
     * @param page
     * @param limit
     * @return
     */
    PageDataResult list(int page, int limit, ChildSearchDTO childSearch);

    /**
     * 设置儿童信息（添加、修改）
     *
     * @param child
     * @return
     */
    String setChild(Child child);

    /**
     * 删除儿童信息
     *
     * @param id
     * @return
     */
    String delete(Integer id);

    /**
     * 根据id查找儿童信息
     *
     * @param id
     * @return
     */
    Child getChildById(Integer id);

    /**
     * 获取所有儿童信息（未删除的）
     *
     * @return
     */
    List<Child> getChildren();
}
