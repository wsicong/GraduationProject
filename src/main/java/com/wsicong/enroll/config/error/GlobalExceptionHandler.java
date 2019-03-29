package com.wsicong.enroll.config.error;

import com.wsicong.enroll.enums.ExceptionEnum;
import com.wsicong.enroll.util.ShiroFilterUtils;
import org.apache.shiro.session.UnknownSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理，包括【普通调用和ajax调用】
 * <p>
 * ControllerAdvice来做controller内部的全局异常处理，
 * 但对于未进入controller前的异常，该处理方法是无法进行捕获处理的，SpringBoot提供了ErrorController的处理类来处理所有的异常(TODO)。
 * <p>
 * 1.当普通调用时，跳转到自定义的错误页面；
 * 2.当ajax调用时，可返回约定的json数据对象，方便页面统一处理。
 */
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ModelAndView errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.debug(getClass().getName() + ".errorHandler】统一异常处理：request=" + request);
        ModelAndView mv = new ModelAndView();
        logger.info(getClass().getName() + ".errorHandler】统一异常处理：" + e.getMessage());
        //1 获取错误状态码
        HttpStatus httpStatus = getStatus(request);
        logger.info(getClass().getName() + ".errorHandler】统一异常处理!错误状态码httpStatus：" + httpStatus);
        //2 返回错误提示
        ExceptionEnum ee = getMessage(httpStatus);
        //3 将错误信息放入mv中
        mv.addObject("type", ee.getType());
        mv.addObject("code", ee.getCode());
        mv.addObject("msg", ee.getMsg());
        if (!ShiroFilterUtils.isAjax(request)) {
            //不是异步请求
            mv.setViewName(DEFAULT_ERROR_VIEW);
            logger.debug(getClass().getName() + ".errorHandler】统一异常处理：普通请求。");
        }
        logger.debug(getClass().getName() + ".errorHandler】统一异常处理响应结果：MV=" + mv);
        return mv;
    }

    /**
     * 处理UnknownSessionException异常【无效！！！因为未进入到controller之前出现，在sessionManager管理器的时候已经抛出异常】
     *
     * @param req
     * @param response
     * @param e
     * @throws Exception
     */
    @ExceptionHandler(value = UnknownSessionException.class)
    public void toExceptionHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        logger.debug(this.getClass().getName() + ".toExceptionHandler】统一异常UnknownSessionException处理：开始===");
        logger.debug("清除cookie操作开始");
        // 删除cookie
        Cookie co = new Cookie("JSESSIONID", "");
        // Cookie co = new Cookie("username", loginName);
        co.setMaxAge(0);// 设置立即过期
        co.setPath("/");// 根目录，整个网站有效
        response.addCookie(co);
        logger.debug("清除cookie完毕！");
    }

    /**
     * 获取错误状态码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 根据error状态码，返回不同的错误提示信息
     *
     * @param httpStatus
     * @return
     */
    @SuppressWarnings("static-access")
    private ExceptionEnum getMessage(HttpStatus httpStatus) {
        if (httpStatus.is4xxClientError()) {
            //4开头的错误状态码
            if ("400".equals(httpStatus.BAD_REQUEST)) {
                return ExceptionEnum.BAD_REQUEST;
            } else if ("403".equals(httpStatus.FORBIDDEN)) {
                return ExceptionEnum.FORBIDDEN;
            } else if ("404".equals(httpStatus.NOT_FOUND)) {
                return ExceptionEnum.NOT_FOUND;
            }
        } else if (httpStatus.is5xxServerError()) {
            //5开头的错误状态码
            if ("500".equals(httpStatus.INTERNAL_SERVER_ERROR)) {
                return ExceptionEnum.SERVER_EPT;
            }
        }
        //统一返回：未知错误
        return ExceptionEnum.UNKNOW_ERROR;
    }
}
