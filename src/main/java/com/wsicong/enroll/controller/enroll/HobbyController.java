package com.wsicong.enroll.controller.enroll;

import com.wsicong.enroll.dto.HobbySearchDTO;
import com.wsicong.enroll.model.Hobby;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.HobbyService;
import com.wsicong.enroll.util.PageDataResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 兴趣班管理Controller
 */
@Controller
@RequestMapping("/hobby")
public class HobbyController {
    private static final Logger logger = LoggerFactory.getLogger(HobbyController.class);

    @Autowired
    private HobbyService hobbyService;

    @RequestMapping("/hobbyList")
    public String toHobbyList() {
        return "/enroll/hobbyList";
    }

    /**
     * 分页查询兴趣列表
     */
    /*@RequiresPermissions("hobbyType")*/
    @PostMapping("/getHobbyList")
    @ResponseBody
    public PageDataResult getHobbyList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HobbySearchDTO hobbySearch) {
        logger.debug("分页查询兴趣分类列表！搜索条件：hobbySearch：" + hobbySearch + ",page:" + page + ",每页记录数量limit:" + limit);

        PageDataResult result = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取兴趣分类列表
            result = hobbyService.list(page, limit, hobbySearch);
            logger.debug("兴趣分类列表查询=result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("兴趣分类列表查询异常！", e);
        }
        return result;
    }

    /**
     * 添加/更新兴趣
     *
     * @param hobby
     * @return
     */
    @PostMapping("/setHobby")
    @ResponseBody
    public String setHobby(Hobby hobby) {
        logger.debug("操作：添加/更新兴趣：" + hobby);
        if ((hobby.getEnable()) == null) {
            hobby.setEnable(false);
        }
        return hobbyService.setHobby(hobby);
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
        logger.debug("删除兴趣类别！id:" + id);
        String msg = "";
        try {
            if (null == id) {
                logger.debug("删除兴趣类别，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            msg = hobbyService.delete(id);
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
    @GetMapping("/getHobby")
    @ResponseBody
    public Map<String, Object> getHobby(@RequestParam("id") Integer id) {
        logger.debug("查询兴趣数据！id：" + id);
        Map<String, Object> map = new HashMap<>();
        try {
            if (null == id) {
                logger.debug("查询兴趣数据==请求参数有误，请您稍后再试");
                map.put("msg", "请求参数有误，请您稍后再试");
                return map;
            }
            //查询兴趣
            Hobby hobby = hobbyService.getHobby(id);
            logger.debug("查询兴趣数据！hobby=" + hobby);
            if (null != hobby) {
                map.put("hobby", hobby);
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
     * 设置是否启用兴趣
     *
     * @param id
     * @param isEnable
     * @return
     */
    @PostMapping("/setHobbyEnable")
    @ResponseBody
    public String setHobbyEnable(@RequestParam("id") Integer id, @RequestParam("isEnable") Integer isEnable) {
        logger.debug("设置兴趣是都启用！id:" + id + ",isEnable:" + isEnable);
        String msg = "";
        try {
            if (null == id || null == isEnable) {
                logger.debug("设置兴趣是否启用，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User existUser = (User) SecurityUtils.getSubject().getPrincipal();
            if (null == existUser) {
                logger.debug("设置兴趣是否启用，结果=您未登录或登录超时，请您登录后再试");
                return "您未登录或登录超时，请您登录后再试";
            }
            //设置兴趣是否启用
            msg = hobbyService.setEnable(id, isEnable);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("设置兴趣是否启用异常！", e);
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }

}
