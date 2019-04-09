package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.dto.EnrollRecordSearchDTO;
import com.wsicong.enroll.mapper.EnrollRecordMapper;
import com.wsicong.enroll.model.EnrollRecord;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.EnrollRecordService;
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
public class EnrollRecordServiceImpl implements EnrollRecordService {
    private static final Logger logger = LoggerFactory.getLogger(EnrollRecordServiceImpl.class);

    @Autowired
    private EnrollRecordMapper enrollRecordMapper;

    @Override
    public PageDataResult list(int page, int limit, EnrollRecordSearchDTO enrollRecordSearch) {
        PageDataResult result = new PageDataResult();
        PageHelper.startPage(page, limit);
        List<EnrollRecord> enrollRecordList = enrollRecordMapper.selectEnrollRecordList(enrollRecordSearch);
        //拿到分页查询后的数据
        PageInfo<EnrollRecord> pageInfo = new PageInfo<>(enrollRecordList);
        // 设置获取到的总记录数total：
        result.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        result.setList(enrollRecordList);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {RuntimeException.class, Exception.class})
    public String setEnrollRecord(EnrollRecord enrollRecord) {
        //获取当前用户
        User existUser = (User) SecurityUtils.getSubject().getPrincipal();
        //id为不为空时，说明为更新操作,否则为新增操作
        if (null != enrollRecord.getId()) {
            enrollRecord.setUpdateTime(new Date());
            enrollRecord.setUpdateBy(existUser.getId().toString());
            enrollRecordMapper.updateByPrimaryKeySelective(enrollRecord);
        } else {
            EnrollRecord existEnrollRecord = this.enrollRecordMapper.selectByChildIdAndClassId(enrollRecord.getChildId(), enrollRecord.getClassId());
            System.out.println("+++++++++++++++++++++" + existEnrollRecord);
            if (null != existEnrollRecord) {
                return "该报名记录信息已存在，不能重复添加";
            } else {
                enrollRecord.setCreateBy(existUser.getId().toString());
                enrollRecord.setCreateTime(new Date());
                enrollRecordMapper.insert(enrollRecord);
            }
        }
        return "ok";
    }

    @Override
    public String delete(Integer id) {
        return enrollRecordMapper.deleteByPrimaryKey(id) == 1 ? "ok" : "删除失败，请您稍后再试";
    }

    @Override
    public EnrollRecord getEnrollRecordById(Integer id) {
        return enrollRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EnrollRecord> getEnrollRecords() {
        return enrollRecordMapper.selectEnrollRecords();
    }
}
