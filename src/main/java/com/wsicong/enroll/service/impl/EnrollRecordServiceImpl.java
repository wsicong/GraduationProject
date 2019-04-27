package com.wsicong.enroll.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsicong.enroll.dto.ChildGuardianDTO;
import com.wsicong.enroll.dto.EnrollRecordSearchDTO;
import com.wsicong.enroll.mapper.ChildMapper;
import com.wsicong.enroll.mapper.EnrollRecordMapper;
import com.wsicong.enroll.mapper.GuardianMapper;
import com.wsicong.enroll.model.Child;
import com.wsicong.enroll.model.EnrollRecord;
import com.wsicong.enroll.model.Guardian;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.EnrollRecordService;
import com.wsicong.enroll.util.DateUtil;
import com.wsicong.enroll.util.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class EnrollRecordServiceImpl implements EnrollRecordService {
    private static final Logger logger = LoggerFactory.getLogger(EnrollRecordServiceImpl.class);

    @Autowired
    private EnrollRecordMapper enrollRecordMapper;

    @Autowired
    private ChildMapper childMapper;

    @Autowired
    private GuardianMapper guardianMapper;

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

    /**
     * 添加报名记录信息：班级信息、少儿信息、监护人信息、报名记录
     *
     * @param childGuardianDTO
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 30000, rollbackFor = {RuntimeException.class, Exception.class})
    public String addEnrollRecord(ChildGuardianDTO childGuardianDTO) {
        //获取当前用户
        User existUser = (User) SecurityUtils.getSubject().getPrincipal();
        try {
            //封装儿童信息
            Child child = new Child();
            child.setChildName(childGuardianDTO.getChildName());
            child.setChildSex(childGuardianDTO.getChildSex());
            Date childBirth = DateUtil.parse(childGuardianDTO.getChildBirthStr() + " 00:00:00");
            child.setChildBirth(childBirth);
            child.setChildAge(childGuardianDTO.getChildAge());
            child.setChildAdress(childGuardianDTO.getChildAdress());
            child.setChildRegion(childGuardianDTO.getChildRegion());
            child.setChildNation(childGuardianDTO.getChildNation());
            child.setChildIdCard(childGuardianDTO.getChildIdCard());
            child.setChildAllergies(childGuardianDTO.getChildAllergies());
            child.setChildMedicalHistory(childGuardianDTO.getChildMedicalHistory());
            child.setIsDel(false);
            child.setCreateBy(existUser.getId().toString());
            child.setCreateTime(new Date());
            //插入后返回的id值
            int childId = childMapper.insert(child);

            //封装监护人信息
            Guardian guardian = new Guardian();
            guardian.setChildId(childId);
            guardian.setGuardianName(childGuardianDTO.getGuardianName());
            guardian.setGuardianSex(childGuardianDTO.getGuardianSex());
            guardian.setGuardianIdCard(childGuardianDTO.getGuardianIdCard());
            guardian.setGuardianRelation(childGuardianDTO.getGuardianRelation());
            guardian.setGuardianPhone(childGuardianDTO.getGuardianPhone());
            guardian.setIsDel(false);
            guardian.setCreateBy(existUser.getId().toString());
            guardian.setCreateTime(new Date());
            //插入监护人信息后返回的id值
            int guardianId = guardianMapper.insert(guardian);

            //封装报名记录信息
            EnrollRecord enrollRecord = new EnrollRecord();
            enrollRecord.setChildId(childId);
            enrollRecord.setGuardianId(guardianId);
            /*enrollRecord.setClassId(childGuardianDTO.getHobbyClassId());*/
            enrollRecord.setClassName(childGuardianDTO.getHobbyClassName());
            enrollRecord.setChildName(childGuardianDTO.getChildName());
            enrollRecord.setGuardianName(childGuardianDTO.getGuardianName());
            enrollRecord.setGuardianPhone(childGuardianDTO.getGuardianPhone());
            enrollRecord.setIsPay(false);
            enrollRecord.setIsDel(false);
            enrollRecord.setCreateBy(existUser.getId().toString());
            enrollRecord.setCreateTime(new Date());
            enrollRecordMapper.insert(enrollRecord);
        } catch (ParseException e) {
            e.printStackTrace();
            return "报名出现异常，请稍后再试";
        } catch (Exception e) {
            e.printStackTrace();
            return "报名出现异常，请稍后再试";
        }
        return "ok";
    }

    /**
     * 修改用户缴费状态
     *
     * @param id
     * @param isPay
     * @return
     */
    @Override
    public String setPayStatus(Integer id, Integer isPay) {
        return enrollRecordMapper.updatePayStatus(id, isPay) == 1 ? "ok" : "操作失败，请您稍后再试";
    }
}
