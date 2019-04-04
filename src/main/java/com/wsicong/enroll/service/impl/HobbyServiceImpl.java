package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.mapper.HobbyMapper;
import com.wsicong.enroll.model.Hobby;
import com.wsicong.enroll.service.HobbyService;
import com.wsicong.enroll.util.PageDataResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HobbyServiceImpl implements HobbyService {
    private static final Logger logger = LoggerFactory.getLogger(HobbyServiceImpl.class);

    @Autowired
    private HobbyMapper hobbyMapper;

    /**
     * 分页查询兴趣分类列表
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageDataResult list(int page, int limit, Hobby hobby) {
        PageDataResult result = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Hobby> hobbyList = hobbyMapper.selectHobbyList(hobby);
        //拿到分页查询后的数据
        PageInfo<Hobby> pageInfo = new PageInfo<>(hobbyList);
        result.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        result.setList(hobbyList);
        return result;
    }

    /**
     * 新增兴趣分类
     *
     * @param hobby
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {RuntimeException.class, Exception.class})
    public String add(Hobby hobby) {
        hobbyMapper.insert(hobby);
        return "ok";
    }

    /**
     * 删除兴趣分类
     *
     * @param id
     * @return
     */
    @Override
    public String delete(Integer id) {
        return hobbyMapper.deleteByPrimaryKey(id) == 1 ? "ok" : "删除失败，请您稍后再试";
    }
}
