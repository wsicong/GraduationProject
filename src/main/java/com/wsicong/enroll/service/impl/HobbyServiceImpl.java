package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.dto.HobbySearchDTO;
import com.wsicong.enroll.mapper.HobbyMapper;
import com.wsicong.enroll.model.Hobby;
import com.wsicong.enroll.service.HobbyService;
import com.wsicong.enroll.util.DateUtil;
import com.wsicong.enroll.util.PageDataResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    public PageDataResult list(int page, int limit, HobbySearchDTO hobbySearch) {
        //时间处理
        /*if (null != hobbySearch) {
            if (StringUtils.isNotEmpty(hobbySearch.getInsertTimeStart()) && StringUtils.isEmpty(hobbySearch.getInsertTimeEnd())) {
                hobbySearch.setInsertTimeEnd(DateUtil.format(new Date()));
            } else if (StringUtils.isEmpty(hobbySearch.getInsertTimeStart()) && StringUtils.isNotEmpty(hobbySearch.getInsertTimeEnd())) {
                hobbySearch.setInsertTimeStart(DateUtil.format(new Date()));
            }
            if (StringUtils.isNotEmpty(hobbySearch.getInsertTimeStart()) && StringUtils.isNotEmpty(hobbySearch.getInsertTimeEnd())) {
                if (hobbySearch.getInsertTimeEnd().compareTo(hobbySearch.getInsertTimeStart()) < 0) {
                    String temp = hobbySearch.getInsertTimeStart();
                    hobbySearch.setInsertTimeStart(hobbySearch.getInsertTimeEnd());
                    hobbySearch.setInsertTimeEnd(temp);
                }
            }
        }*/
        PageDataResult result = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Hobby> hobbyList = hobbyMapper.selectHobbyList(hobbySearch);
        //拿到分页查询后的数据
        PageInfo<Hobby> pageInfo = new PageInfo<>(hobbyList);
        // 设置获取到的总记录数total：
        result.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        result.setList(hobbyList);
        return result;
    }

    /**
     * 添加/更新兴趣
     *
     * @param hobby
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {RuntimeException.class, Exception.class})
    public String setHobby(Hobby hobby) {
        //id为不为空时，说明为更新操作,否则为新增操作
        if (null != hobby.getId()) {
            hobby.setUpdateTime(new Date());
            hobbyMapper.updateByPrimaryKeySelective(hobby);
        } else {
            Hobby existHobby = this.hobbyMapper.selectByHobbyName(hobby.getHobbyName());
            System.out.println("+++++++++++++++++++++" + existHobby);
            if (null != existHobby) {
                return "该兴趣已存在，不能重复添加";
            } else {
                hobby.setCreateTime(new Date());
                hobbyMapper.insert(hobby);
            }
        }
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

    /**
     * 根据id查找兴趣
     *
     * @param id
     * @return
     */
    @Override
    public Hobby getHobby(Integer id) {
        return hobbyMapper.selectByPrimaryKey(id);
    }


    /**
     * 设置是否启用兴趣
     *
     * @param id
     * @param isEnable
     * @return
     */
    @Override
    public String setEnable(Integer id, Integer isEnable) {
        return hobbyMapper.updateEnable(id, isEnable) == 1 ? "ok" : "操作失败，请您稍后再试";
    }
}
