package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.dto.HobbyClassSearchDTO;
import com.wsicong.enroll.mapper.HobbyClassMapper;
import com.wsicong.enroll.model.HobbyClass;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.HobbyClassService;
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
public class HobbyClassServiceImpl implements HobbyClassService {
    private static final Logger logger = LoggerFactory.getLogger(HobbyClassServiceImpl.class);

    @Autowired
    private HobbyClassMapper hobbyClassMapper;


    /**
     * 查询所有兴趣班
     *
     * @param hobbyClassSearchDTO
     * @return
     */
    @Override
    public List<HobbyClass> listHobbyClass(HobbyClassSearchDTO hobbyClassSearchDTO) {
        List<HobbyClass> hobbyClassList = hobbyClassMapper.selectHobbyClassList(hobbyClassSearchDTO);
        return hobbyClassList;
    }

    @Override
    public PageDataResult listUserClass(int page, int limit, HobbyClassSearchDTO hobbyClassSearch) {
        //更新班级状态
        setStatus();
        PageDataResult result = new PageDataResult();
        hobbyClassSearch.setStatus(1);
        hobbyClassSearch.setEnable(true);
        PageHelper.startPage(page, limit);
        List<HobbyClass> hobbyClassList = hobbyClassMapper.selectHobbyClassList(hobbyClassSearch);
        PageInfo<HobbyClass> pageInfo = new PageInfo<>(hobbyClassList);
        result.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        result.setList(hobbyClassList);
        return result;
    }

    @Override
    public PageDataResult list(int page, int limit, HobbyClassSearchDTO hobbyClassSearch) {
        //更新班级状态
        setStatus();
        PageDataResult result = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<HobbyClass> hobbyClassList = hobbyClassMapper.selectHobbyClassList(hobbyClassSearch);
        //拿到分页查询后的数据
        PageInfo<HobbyClass> pageInfo = new PageInfo<>(hobbyClassList);
        // 设置获取到的总记录数total：
        result.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        result.setList(hobbyClassList);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {RuntimeException.class, Exception.class})
    public String setHobbyClass(HobbyClass hobbyClass) {
        //获取当前用户
        User existUser = (User) SecurityUtils.getSubject().getPrincipal();
        //id为不为空时，说明为更新操作,否则为新增操作
        if (null != hobbyClass.getId()) {
            hobbyClass.setUpdateTime(new Date());
            hobbyClass.setUpdateBy(existUser.getId().toString());
            hobbyClassMapper.updateByPrimaryKeySelective(hobbyClass);
        } else {
            HobbyClass existHobbyClass = this.hobbyClassMapper.selectByHobbyClassName(hobbyClass.getClassName());
            System.out.println("+++++++++++++++++++++" + existHobbyClass);
            if (null != existHobbyClass) {
                return "该兴趣班级已存在，不能重复添加";
            } else {
                hobbyClass.setStatus(1);
                hobbyClass.setEnrolledNum(0);
                hobbyClass.setCreateTime(new Date());
                hobbyClass.setCreateBy(existUser.getId().toString());
                hobbyClassMapper.insert(hobbyClass);
            }
        }
        return "ok";
    }

    @Override
    public String delete(Integer id) {
        return hobbyClassMapper.deleteByPrimaryKey(id) == 1 ? "ok" : "删除失败，请您稍后再试";
    }

    @Override
    public HobbyClass getHobbyClass(Integer id) {
        return hobbyClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public String setEnable(Integer id, Integer isEnable) {
        return hobbyClassMapper.updateEnable(id, isEnable) == 1 ? "ok" : "操作失败，请您稍后再试";
    }

    @Override
    public String setStatus() {
        hobbyClassMapper.updateEnrollStatus();
        hobbyClassMapper.updateStartStatus();
        hobbyClassMapper.updateEndStatus();
        return "ok";
    }


}
