package com.wsicong.enroll.controller.enroll;


import com.wsicong.enroll.dto.ChildGuardianDTO;
import com.wsicong.enroll.dto.EnrollRecordSearchDTO;
import com.wsicong.enroll.model.EnrollRecord;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.EnrollRecordService;
import com.wsicong.enroll.util.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报名记录Controller
 */
@Controller
@RequestMapping("/enrollRecord")
public class EnrollRecordController {
    private static final Logger logger = LoggerFactory.getLogger(EnrollRecordController.class);

    @Autowired
    private EnrollRecordService enrollRecordService;

    @RequestMapping("/enrollList")
    public String toEnrollList() {
        return "/enroll/enrollList";
    }

    @RequestMapping("/enrollFrom")
    public String toEnrollFrom() {
        return "/enroll/enrollFrom";
    }

    @RequestMapping("/enrollResult")
    public String toEnrollResult() {
        return "/enroll/enrollResult";
    }

    @RequestMapping("/enrollResultEcharts")
    public String toEnrollResultEcharts() {
        return "/enroll/enrollResultEcharts";
    }
    /**
     * 分页查询报名记录列表
     */
    /*@RequiresPermissions("hobbyType")*/
    @PostMapping("/getEnrollRecordList")
    @ResponseBody
    public PageDataResult getHobbyList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, EnrollRecordSearchDTO enrollRecordSearch) {
        logger.debug("分页查询报名记录信息列表！搜索条件：enrollRecordSearch：" + enrollRecordSearch + ",page:" + page + ",每页记录数量limit:" + limit);

        //获取当前用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        enrollRecordSearch.setUserId(user.getId());

        PageDataResult result = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取报名记录信息列表
            result = enrollRecordService.list(page, limit, enrollRecordSearch);
            logger.debug("报名记录列表查询=result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("报名记录列表查询异常！", e);
        }
        return result;
    }

    /**
     * 添加/更新报名记录信息
     *
     * @param enrollRecord
     * @return
     */
    @PostMapping("/setEnrollRecord")
    @ResponseBody
    public String setEnrollRecord(EnrollRecord enrollRecord) {
        logger.debug("操作：添加/更新报名记录信息：" + enrollRecord);
        return enrollRecordService.setEnrollRecord(enrollRecord);
    }

    /**
     * 删除报名记录信息
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        logger.debug("删除报名记录信息！id:" + id);
        String msg = "";
        try {
            if (null == id) {
                logger.debug("删除报名记录信息，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            msg = enrollRecordService.delete(id);
            logger.info("删除报名记录信息：" + msg + "。enrollRecordId=" + id + ",操作用户id：" + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除报名记录信息异常！", e);
            msg = "操作异常，请您稍后再试";
        }
        return msg;
    }

    /**
     * 根据id查询报名记录信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getEnrollRecordById")
    @ResponseBody
    public Map<String, Object> getEnrollRecord(@RequestParam("id") Integer id) {
        logger.debug("查询兴趣数据！id：" + id);
        Map<String, Object> map = new HashMap<>();
        try {
            if (null == id) {
                logger.debug("查询报名记录信息==请求参数有误，请您稍后再试");
                map.put("msg", "请求参数有误，请您稍后再试");
                return map;
            }
            //查询报名记录信息
            EnrollRecord enrollRecord = enrollRecordService.getEnrollRecordById(id);
            logger.debug("查询报名记录信息！enrollRecord=" + enrollRecord);
            if (null != enrollRecord) {
                map.put("enrollRecord", enrollRecord);
                map.put("msg", "ok");
            } else {
                map.put("msg", "查询报名记录信息有误，请您稍后再试");
            }
            logger.debug("查询报名记录信息成功！map=" + map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询报名记录信息有误，请您稍后再试！");
            logger.error("查询报名记录信息异常！", e);
        }
        return map;
    }

    @GetMapping("/getEnrollRecords")
    @ResponseBody
    public List<EnrollRecord> getEnrollRecords() {
        logger.debug("查找所有报名记录（未删除）！");
        List<EnrollRecord> enrollRecords = null;
        try {
            return enrollRecordService.getEnrollRecords();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查找所有报名记录（未删除）异常！", e);
        }
        return enrollRecords;
    }

    /**
     * 添加报名记录信息
     *
     * @param childGuardianDTO
     * @return
     */
    @PostMapping("/addEnrollRecord")
    @ResponseBody
    public String addEnrollRecord(ChildGuardianDTO childGuardianDTO) {
        logger.debug("操作：添加报名记录信息：" + childGuardianDTO);
        return enrollRecordService.addEnrollRecord(childGuardianDTO);
    }

    /**
     * 设置用户缴费状态
     *
     * @param id
     * @param isPay
     * @return
     */
    @PostMapping("/setPayStatus")
    @ResponseBody
    public String setPayStatus(@RequestParam("id") Integer id, @RequestParam("isPay") Integer isPay) {
        logger.debug("设置用户是否缴费！id:" + id + ",isPay:" + isPay);
        String msg = "";
        try {
            if (null == id || null == isPay) {
                logger.debug("设置用户是否缴费，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("设置用户是否缴费，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            //设置兴趣是否启用
            msg = enrollRecordService.setPayStatus(id, isPay);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("设置用户是否缴费异常！", e);
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }
}
