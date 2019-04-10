package com.wsicong.enroll.controller.enroll;


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

    @RequestMapping("/enrollRecordList")
    public String toEnrollRecordList() {
        return "/enroll/enrollRecordList";
    }

    /**
     * 分页查询报名记录列表
     */
    /*@RequiresPermissions("hobbyType")*/
    @PostMapping("/getEnrollRecordList")
    @ResponseBody
    public PageDataResult getHobbyList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, EnrollRecordSearchDTO enrollRecordSearch) {
        logger.debug("分页查询报名记录信息列表！搜索条件：enrollRecordSearch：" + enrollRecordSearch + ",page:" + page + ",每页记录数量limit:" + limit);

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

    @GetMapping("/getEnrollRecordren")
    @ResponseBody
    public List<EnrollRecord> getEnrollRecordren() {
        logger.debug("查找所有报名记录（未删除）！");
        List<EnrollRecord> enrollRecordren = null;
        try {
            return enrollRecordService.getEnrollRecords();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查找所有监护人（未删除）异常！", e);
        }
        return enrollRecordren;
    }
}
