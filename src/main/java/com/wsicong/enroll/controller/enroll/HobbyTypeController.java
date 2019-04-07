package com.wsicong.enroll.controller.enroll;

import com.wsicong.enroll.dto.HobbyTypeSearchDTO;
import com.wsicong.enroll.model.HobbyType;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.HobbyTypeService;
import com.wsicong.enroll.util.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 兴趣分类管理Controller
 */
@Controller
@RequestMapping("/hobbyType")
public class HobbyTypeController {
    private static final Logger logger = LoggerFactory.getLogger(HobbyTypeController.class);

    @Autowired
    private HobbyTypeService hobbyTypeService;

    @RequestMapping("/hobbyTypeList")
    public String toHobbyList() {
        return "/enroll/hobbyTypeList";
    }

    /**
     * 分页查询兴趣分类列表
     */
    /*@RequiresPermissions("hobbyTypeList")*/
    @PostMapping("/getHobbyTypeList")
    @ResponseBody
    public PageDataResult getHobbyList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HobbyTypeSearchDTO hobbyTypeSearch) {
        logger.debug("分页查询兴趣分类列表！搜索条件：hobbySearch：" + hobbyTypeSearch + ",page:" + page + ",每页记录数量limit:" + limit);

        PageDataResult result = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取兴趣分类列表
            result = hobbyTypeService.list(page, limit, hobbyTypeSearch);
            logger.debug("兴趣分类列表查询=result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("兴趣分类列表查询异常！", e);
        }
        return result;
    }

    /**
     * 添加/更新兴趣分类
     *
     * @param hobbyType
     * @return
     */
    @PostMapping("/setHobbyType")
    @ResponseBody
    public String setHobby(HobbyType hobbyType) {
        logger.debug("操作：添加/更新兴趣：" + hobbyType);
        if ((hobbyType.getEnable()) == null) {
            hobbyType.setEnable(false);
        }
        return hobbyTypeService.setHobbyType(hobbyType);
    }

    /**
     * 删除兴趣类别
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        logger.debug("删除兴趣分类！id:" + id);
        String msg = "";
        try {
            if (null == id) {
                logger.debug("删除兴趣分类，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            msg = hobbyTypeService.delete(id);
            logger.info("删除兴趣分类：" + msg + "。hobbyId=" + id + ",操作用户id：" + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除兴趣分类异常！", e);
            msg = "操作异常，请您稍后再试";
        }
        return msg;
    }

    /**
     * 根据id查询兴趣数据
     *
     * @param id
     * @return
     */
    @GetMapping("/getHobbyType")
    @ResponseBody
    public Map<String, Object> getHobby(@RequestParam("id") Integer id) {
        logger.debug("查询兴趣数据！id：" + id);
        Map<String, Object> map = new HashMap<>();
        try {
            if (null == id) {
                logger.debug("查询兴趣分类数据==请求参数有误，请您稍后再试");
                map.put("msg", "请求参数有误，请您稍后再试");
                return map;
            }
            //查询兴趣
            HobbyType hobbyType = hobbyTypeService.getHobbyType(id);
            logger.debug("查询兴趣数据！hobbyType=" + hobbyType);
            if (null != hobbyType) {
                map.put("hobbyType", hobbyType);
                map.put("msg", "ok");
            } else {
                map.put("msg", "查询兴趣信息有误，请您稍后再试");
            }
            logger.debug("查询兴趣数据成功！map=" + map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询兴趣数据有误，请您稍后再试！");
            logger.error("查询兴趣数据异常！", e);
        }
        return map;
    }

    /**
     * 设置是否启用兴趣分类
     *
     * @param id
     * @param isEnable
     * @return
     */
    @PostMapping("/setHobbyTypeEnable")
    @ResponseBody
    public String setHobbyTypeEnable(@RequestParam("id") Integer id, @RequestParam("isEnable") Integer isEnable) {
        logger.debug("设置兴趣分类是否启用！id:" + id + ",isEnable:" + isEnable);
        String msg = "";
        try {
            if (null == id || null == isEnable) {
                logger.debug("设置兴趣分类是否启用，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("设置兴趣分类是否启用，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            //设置兴趣是否启用
            msg = hobbyTypeService.setEnable(id, isEnable);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("设置兴趣分类是否启用异常！", e);
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }
}
