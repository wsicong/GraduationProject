package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.dto.GuardianSearchDTO;
import com.wsicong.enroll.mapper.GuardianMapper;
import com.wsicong.enroll.model.Guardian;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.GuardianService;
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
public class GuardianServiceImpl implements GuardianService {
    private static final Logger logger = LoggerFactory.getLogger(GuardianServiceImpl.class);

    @Autowired
    private GuardianMapper guardianMapper;

    @Override
    public PageDataResult list(int page, int limit, GuardianSearchDTO guardianSearch) {
        PageDataResult result = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Guardian> guardianList = guardianMapper.selectGuardianList(guardianSearch);
        //拿到分页查询后的数据
        PageInfo<Guardian> pageInfo = new PageInfo<>(guardianList);
        // 设置获取到的总记录数total：
        result.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        result.setList(guardianList);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {RuntimeException.class, Exception.class})
    public String setGuardian(Guardian guardian) {
        //获取当前用户
        User existUser = (User) SecurityUtils.getSubject().getPrincipal();
        //id为不为空时，说明为更新操作,否则为新增操作
        if (null != guardian.getId()) {
            guardian.setUpdateTime(new Date());
            guardian.setUpdateBy(existUser.getId().toString());
            guardianMapper.updateByPrimaryKeySelective(guardian);
        } else {
            Guardian existGuardian = this.guardianMapper.selectByGuardianName(guardian.getGuardianName());
            System.out.println("+++++++++++++++++++++" + existGuardian);
            if (null != existGuardian) {
                return "该监护人信息已存在，不能重复添加";
            } else {
                guardian.setCreateBy(existUser.getId().toString());
                guardian.setCreateTime(new Date());
                guardianMapper.insert(guardian);
            }
        }
        return "ok";
    }

    @Override
    public String delete(Integer id) {
        return guardianMapper.deleteByPrimaryKey(id) == 1 ? "ok" : "删除失败，请您稍后再试";
    }

    @Override
    public Guardian getGuardianById(Integer id) {
        return guardianMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Guardian> getGuardians() {
        return guardianMapper.selectGuardian();
    }
}
