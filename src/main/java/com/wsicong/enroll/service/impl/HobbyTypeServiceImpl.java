package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.dto.HobbyTypeSearchDTO;
import com.wsicong.enroll.mapper.HobbyTypeMapper;
import com.wsicong.enroll.model.HobbyType;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.HobbyTypeService;
import com.wsicong.enroll.util.PageDataResult;
import org.apache.shiro.SecurityUtils;
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
public class HobbyTypeServiceImpl implements HobbyTypeService {
    private static final Logger logger = LoggerFactory.getLogger(HobbyTypeServiceImpl.class);

    @Autowired
    private HobbyTypeMapper hobbyTypeMapper;

    /**
     * 分页查询兴趣分类列表
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageDataResult list(int page, int limit, HobbyTypeSearchDTO hobbyTypeSearch) {
        //时间处理
        /*if (null != hobbyTypeSearch) {
            if (StringUtils.isNotEmpty(hobbyTypeSearch.getInsertTimeStart()) && StringUtils.isEmpty(hobbyTypeSearch.getInsertTimeEnd())) {
                hobbyTypeSearch.setInsertTimeEnd(DateUtil.format(new Date()));
            } else if (StringUtils.isEmpty(hobbyTypeSearch.getInsertTimeStart()) && StringUtils.isNotEmpty(hobbyTypeSearch.getInsertTimeEnd())) {
                hobbyTypeSearch.setInsertTimeStart(DateUtil.format(new Date()));
            }
            if (StringUtils.isNotEmpty(hobbyTypeSearch.getInsertTimeStart()) && StringUtils.isNotEmpty(hobbyTypeSearch.getInsertTimeEnd())) {
                if (hobbyTypeSearch.getInsertTimeEnd().compareTo(hobbyTypeSearch.getInsertTimeStart()) < 0) {
                    String temp = hobbyTypeSearch.getInsertTimeStart();
                    hobbyTypeSearch.setInsertTimeStart(hobbyTypeSearch.getInsertTimeEnd());
                    hobbyTypeSearch.setInsertTimeEnd(temp);
                }
            }
        }*/
        PageDataResult result = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<HobbyType> hobbyTypeList = hobbyTypeMapper.selectHobbyTypeList(hobbyTypeSearch);
        //拿到分页查询后的数据
        PageInfo<HobbyType> pageInfo = new PageInfo<>(hobbyTypeList);
        // 设置获取到的总记录数total：
        result.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        result.setList(hobbyTypeList);
        return result;
    }

    /**
     * 添加/更新兴趣
     *
     * @param hobbyType
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {RuntimeException.class, Exception.class})
    public String setHobbyType(HobbyType hobbyType) {
        //获取当前用户
        User existUser = (User) SecurityUtils.getSubject().getPrincipal();
        //id为不为空时，说明为更新操作,否则为新增操作
        if (null != hobbyType.getId()) {
            hobbyType.setUpdateTime(new Date());
            hobbyType.setUpdateBy(existUser.getId().toString());
            hobbyTypeMapper.updateByPrimaryKeySelective(hobbyType);
        } else {
            HobbyType existHobbyType = this.hobbyTypeMapper.selectByHobbyTypeName(hobbyType.getHobbyTypeName());
            System.out.println("+++++++++++++++++++++" + existHobbyType);
            if (null != existHobbyType) {
                return "该兴趣已存在，不能重复添加";
            } else {
                hobbyType.setCreateTime(new Date());
                hobbyType.setCreateBy(existUser.getId().toString());
                hobbyTypeMapper.insert(hobbyType);
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
        return hobbyTypeMapper.deleteByPrimaryKey(id) == 1 ? "ok" : "删除失败，请您稍后再试";
    }

    /**
     * 根据id查找兴趣分类
     *
     * @param id
     * @return
     */
    @Override
    public HobbyType getHobbyType(Integer id) {
        return hobbyTypeMapper.selectByPrimaryKey(id);
    }


    /**
     * 设置是否启用兴趣分类
     *
     * @param id
     * @param isEnable
     * @return
     */
    @Override
    public String setEnable(Integer id, Integer isEnable) {
        return hobbyTypeMapper.updateEnable(id, isEnable) == 1 ? "ok" : "操作失败，请您稍后再试";
    }
}
