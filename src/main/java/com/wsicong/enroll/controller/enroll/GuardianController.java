package com.wsicong.enroll.controller.enroll;

import com.wsicong.enroll.dto.GuardianSearchDTO;
import com.wsicong.enroll.model.Guardian;
import com.wsicong.enroll.model.User;
import com.wsicong.enroll.service.GuardianService;
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
 * 监护人信息Controller
 */
@Controller
@RequestMapping("/guardian")
public class GuardianController {
    private static final Logger logger = LoggerFactory.getLogger(GuardianController.class);

    @Autowired
    private GuardianService guardianService;

    @RequestMapping("/guardianList")
    public String toGuardianList() {
        return "/enroll/guardianList";
    }

    /**
     * 分页查询监护人列表
     */
    /*@RequiresPermissions("hobbyType")*/
    @PostMapping("/getGuardianList")
    @ResponseBody
    public PageDataResult getHobbyList(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, GuardianSearchDTO guardianSearch) {
        logger.debug("分页查询监护人信息列表！搜索条件：guardianSearch：" + guardianSearch + ",page:" + page + ",每页记录数量limit:" + limit);

        PageDataResult result = new PageDataResult();
        try {
            if (null == page) {
                page = 1;
            }
            if (null == limit) {
                limit = 10;
            }
            // 获取监护人信息列表
            result = guardianService.list(page, limit, guardianSearch);
            logger.debug("监护人列表查询=result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("监护人列表查询异常！", e);
        }
        return result;
    }

    /**
     * 添加/更新监护人信息
     *
     * @param guardian
     * @return
     */
    @PostMapping("/setGuardian")
    @ResponseBody
    public String setGuardian(Guardian guardian) {
        logger.debug("操作：添加/更新监护人信息：" + guardian);
        return guardianService.setGuardian(guardian);
    }

    /**
     * 删除监护人信息
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        logger.debug("删除监护人信息！id:" + id);
        String msg = "";
        try {
            if (null == id) {
                logger.debug("删除监护人信息，结果=请求参数有误，请您稍后再试");
                return "请求参数有误，请您稍后再试";
            }
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            msg = guardianService.delete(id);
            logger.info("删除监护人信息：" + msg + "。guardianId=" + id + ",操作用户id：" + user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除监护人信息异常！", e);
            msg = "操作异常，请您稍后再试";
        }
        return msg;
    }

    /**
     * 根据id查询监护人信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getGuardianById")
    @ResponseBody
    public Map<String, Object> getGuardian(@RequestParam("id") Integer id) {
        logger.debug("查询兴趣数据！id：" + id);
        Map<String, Object> map = new HashMap<>();
        try {
            if (null == id) {
                logger.debug("查询监护人信息==请求参数有误，请您稍后再试");
                map.put("msg", "请求参数有误，请您稍后再试");
                return map;
            }
            //查询监护人信息
            Guardian guardian = guardianService.getGuardianById(id);
            logger.debug("查询监护人信息！guardian=" + guardian);
            if (null != guardian) {
                map.put("guardian", guardian);
                map.put("msg", "ok");
            } else {
                map.put("msg", "查询监护人信息有误，请您稍后再试");
            }
            logger.debug("查询监护人信息成功！map=" + map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询监护人信息有误，请您稍后再试！");
            logger.error("查询监护人信息异常！", e);
        }
        return map;
    }

    @GetMapping("/getGuardians")
    @ResponseBody
    public List<Guardian> getGuardians() {
        logger.debug("查找所有监护人（未删除）！");
        List<Guardian> guardians = null;
        try {
            return guardianService.getGuardians();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查找所有监护人（未删除）异常！", e);
        }
        return guardians;
    }
}
