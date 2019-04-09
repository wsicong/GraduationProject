package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.dto.ChildSearchDTO;
import com.wsicong.enroll.mapper.ChildMapper;
import com.wsicong.enroll.model.Child;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.ChildService;
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
public class ChildServiceImpl implements ChildService {
    private static final Logger logger = LoggerFactory.getLogger(ChildServiceImpl.class);

    @Autowired
    private ChildMapper childMapper;

    @Override
    public PageDataResult list(int page, int limit, ChildSearchDTO childSearch) {
        PageDataResult result = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Child> childList = childMapper.selectChildList(childSearch);
        //拿到分页查询后的数据
        PageInfo<Child> pageInfo = new PageInfo<>(childList);
        // 设置获取到的总记录数total：
        result.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        result.setList(childList);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {RuntimeException.class, Exception.class})
    public String setChild(Child child) {
        //获取当前用户
        User existUser = (User) SecurityUtils.getSubject().getPrincipal();
        //id为不为空时，说明为更新操作,否则为新增操作
        if (null != child.getId()) {
            child.setUpdateTime(new Date());
            child.setUpdateBy(existUser.getId().toString());
            childMapper.updateByPrimaryKeySelective(child);
        } else {
            Child existChild = this.childMapper.selectByChildName(child.getChildName());
            System.out.println("+++++++++++++++++++++" + existChild);
            if (null != existChild) {
                return "该儿童信息已存在，不能重复添加";
            } else {
                child.setCreateBy(existUser.getId().toString());
                child.setCreateTime(new Date());
                childMapper.insert(child);
            }
        }
        return "ok";
    }

    @Override
    public String delete(Integer id) {
        return childMapper.deleteByPrimaryKey(id) == 1 ? "ok" : "删除失败，请您稍后再试";
    }

    @Override
    public Child getChildById(Integer id) {
        return childMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Child> getChildren() {
        return childMapper.selectChild();
    }
}
