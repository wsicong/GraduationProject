package com.wsicong.enroll.controller.enroll;

import com.wsicong.enroll.dto.ChildSearchDTO;
import com.wsicong.enroll.model.Child;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.ChildService;
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
 * 儿童信息Controller
 */
@Controller
@RequestMapping("/child")
public class ChildController {
    private static final Logger logger = LoggerFactory.getLogger(ChildController.class);

    @Autowired
    private ChildService childService;

    @RequestMapping("/childList")
    public String toChildList() {
        return "/enroll/childList";
    }

    /**
     * 分页查询儿童列表
     */
    /*@RequiresPermissions("hobbyType")*/
    @PostMapping("/getChildList")
    @ResponseBody
    public PageDataResult getHobbyList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, ChildSearchDTO childSearch) {
        logger.debug("分页查询儿童信息列表！搜索条件：childSearch：" + childSearch + ",page:" + page + ",每页记录数量limit:" + limit);

        PageDataResult result = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取儿童信息列表
            result = childService.list(page, limit, childSearch);
            logger.debug("儿童列表查询=result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("儿童列表查询异常！", e);
        }
        return result;
    }

    /**
     * 添加/更新儿童信息
     *
     * @param child
     * @return
     */
    @PostMapping("/setChild")
    @ResponseBody
    public String setChild(Child child) {
        logger.debug("操作：添加/更新儿童信息：" + child);
        return childService.setChild(child);
    }

    /**
     * 删除儿童信息
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        logger.debug("删除儿童信息！id:" + id);
        String msg = "";
        try {
            if (null == id) {
                logger.debug("删除儿童信息，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            msg = childService.delete(id);
            logger.info("删除儿童信息：" + msg + "。childId=" + id + ",操作用户id：" + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除儿童信息异常！", e);
            msg = "操作异常，请您稍后再试";
        }
        return msg;
    }

    /**
     * 根据id查询儿童信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getChildById")
    @ResponseBody
    public Map<String, Object> getChild(@RequestParam("id") Integer id) {
        logger.debug("查询兴趣数据！id：" + id);
        Map<String, Object> map = new HashMap<>();
        try {
            if (null == id) {
                logger.debug("查询儿童信息==请求参数有误，请您稍后再试");
                map.put("msg", "请求参数有误，请您稍后再试");
                return map;
            }
            //查询儿童信息
            Child child = childService.getChildById(id);
            logger.debug("查询儿童信息！child=" + child);
            if (null != child) {
                map.put("child", child);
                map.put("msg", "ok");
            } else {
                map.put("msg", "查询儿童信息有误，请您稍后再试");
            }
            logger.debug("查询儿童信息成功！map=" + map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询儿童信息有误，请您稍后再试！");
            logger.error("查询儿童信息异常！", e);
        }
        return map;
    }

    @GetMapping("/getChildren")
    @ResponseBody
    public List<Child> getChildren() {
        logger.debug("查找所有儿童（未删除）！");
        List<Child> children = null;
        try {
            return childService.getChildren();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查找所有监护人（未删除）异常！", e);
        }
        return children;
    }

}
