package com.wsicong.enroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 手动error异常
 */
@Controller
@RequestMapping("/error")
public class IndexErrorController {
    /**
     * 普通请求异常
     */
    @RequestMapping("getError")
    public void toError() {
        System.out.println(1 / 0);
    }

    /**
     * 异步异常
     *
     * @return
     */
    @RequestMapping("ajaxError")
    @ResponseBody
    public String ajaxError() {
        System.out.println(1 / 0);
        return "异步请求成功！";
    }
}
