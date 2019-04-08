package com.wsicong.enroll.controller.enroll;


import com.wsicong.enroll.dto.HobbyClassSearchDTO;
import com.wsicong.enroll.model.HobbyClass;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.HobbyClassService;
import com.wsicong.enroll.util.DateUtil;
import com.wsicong.enroll.util.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/hobbyClass")
public class HobbyClassController {
    private static final Logger logger = LoggerFactory.getLogger(HobbyClassController.class);

    @Autowired
    private HobbyClassService hobbyClassService;

    @RequestMapping("/hobbyClassList")
    public String toHobbyClassList() {
        return "/enroll/hobbyClassList";
    }

    /**
     * 分页查询兴趣班级列表
     */
    /*@RequiresPermissions("hobbyTypeList")*/
    @PostMapping("/getHobbyClassList")
    @ResponseBody
    public PageDataResult getHobbyList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HobbyClassSearchDTO hobbyClassSearch) {
        logger.debug("分页查询兴趣班级列表！搜索条件：hobbyClassSearch：" + hobbyClassSearch + ",page:" + page + ",每页记录数量limit:" + limit);

        PageDataResult result = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取兴趣班级列表
            result = hobbyClassService.list(page, limit, hobbyClassSearch);
            logger.debug("兴趣班级列表查询=result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("兴趣班级列表查询异常！", e);
        }
        return result;
    }

    /**
     * 添加/更新兴趣班级
     *
     * @param hobbyClass
     * @return
     */
    @PostMapping("/setHobbyClass")
    @ResponseBody
    public String setHobby(HobbyClass hobbyClass,
                           @RequestParam("classStartDateStr") String classStartDateStr,
                           @RequestParam("classEndDateStr") String classEndDateStr,
                           @RequestParam("classStartTimeStr") String classStartTimeStr,
                           @RequestParam("classEndTimeStr") String classEndTimeStr,
                           @RequestParam("studentAgeRange") String studentAgeRange) {

        System.out.println("-----------------------!!!!!!!!!!!" + classStartDateStr + "!!" + classEndDateStr + "!!" + classStartTimeStr + "!!" + classEndTimeStr);

        //设置年龄范围
        hobbyClass.setStudentAge(studentAgeRange);

        //String转Date日期
        try {

            Date classStartDate = DateUtil.parse((classStartDateStr.split(","))[0] + " 00:00:00");
            Date classEndDate = DateUtil.parse((classEndDateStr.split(","))[0] + " 00:00:00");

            Date classStartTime = DateUtil.parse("0000-00-00 " + (classStartTimeStr.split(","))[0]);
            Date classEndTime = DateUtil.parse("0000-00-00 " + (classEndTimeStr.split(","))[0]);

            hobbyClass.setClassStartDate(classStartDate);
            hobbyClass.setClassEndDate(classEndDate);
            hobbyClass.setClassStartTime(classStartTime);
            hobbyClass.setClassEndTime(classEndTime);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("操作：添加/更新兴趣班级：" + hobbyClass);
        if ((hobbyClass.getEnable()) == null) {
            hobbyClass.setEnable(false);
        }
        return hobbyClassService.setHobbyClass(hobbyClass);
    }

    /**
     * 删除兴趣班级
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        logger.debug("删除兴趣班级！id:" + id);
        String msg = "";
        try {
            if (null == id) {
                logger.debug("删除兴趣班级，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            msg = hobbyClassService.delete(id);
            logger.info("删除兴趣班级：" + msg + "。hobbyId=" + id + ",操作用户id：" + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除兴趣分类异常！", e);
            msg = "操作异常，请您稍后再试";
        }
        return msg;
    }

    /**
     * 根据id查询兴趣班级数据
     *
     * @param id
     * @return
     */
    @GetMapping("/getHobbyClass")
    @ResponseBody
    public Map<String, Object> getHobbyClass(@RequestParam("id") Integer id) {
        logger.debug("查询兴趣班级数据！id：" + id);
        Map<String, Object> map = new HashMap<>();
        try {
            if (null == id) {
                logger.debug("查询兴趣班级数据==请求参数有误，请您稍后再试");
                map.put("msg", "请求参数有误，请您稍后再试");
                return map;
            }
            //查询兴趣班级
            HobbyClass hobbyClass = hobbyClassService.getHobbyClass(id);
            logger.debug("查询兴趣班级数据！hobbyClass=" + hobbyClass);
            if (null != hobbyClass) {
                map.put("hobbyClass", hobbyClass);
                map.put("msg", "ok");
            } else {
                map.put("msg", "查询兴趣班级信息有误，请您稍后再试");
            }
            logger.debug("查询兴趣班级数据成功！map=" + map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询兴趣数据有误，请您稍后再试！");
            logger.error("查询兴趣数据异常！", e);
        }
        return map;
    }

    /**
     * 设置是否启用兴趣班级
     *
     * @param id
     * @param isEnable
     * @return
     */
    @PostMapping("/setHobbyClassEnable")
    @ResponseBody
    public String setHobbyClassEnable(@RequestParam("id") Integer id, @RequestParam("isEnable") Integer isEnable) {
        logger.debug("设置兴趣班级是否启用！id:" + id + ",isEnable:" + isEnable);
        String msg = "";
        try {
            if (null == id || null == isEnable) {
                logger.debug("设置兴趣班级是否启用，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("设置兴趣班级是否启用，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            //设置兴趣班级是否启用
            msg = hobbyClassService.setEnable(id, isEnable);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("设置兴趣班级是否启用异常！", e);
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }
}
