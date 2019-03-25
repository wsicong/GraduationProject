package com.wsicong.enroll.handle;

import com.wsicong.enroll.core.Result;
import com.wsicong.enroll.exception.YTXException;
import com.wsicong.enroll.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class YTXExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(YTXExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        e.printStackTrace();
        //业务异常
        if(e instanceof YTXException){
            YTXException ytxException=(YTXException) e;
            return ResultUtil.error(ytxException.getCode(),ytxException.getMessage());
        }else if (e instanceof BindException){
            BindException exception=(BindException)e;
            return ResultUtil.error(-99,
                    "参数:["+exception.getFieldError().getField()+"]错误," +exception.getFieldError().getDefaultMessage());
        }else if(e instanceof MethodArgumentNotValidException){
            //参数传入异常
            MethodArgumentNotValidException exception=(MethodArgumentNotValidException)e;

            BindingResult result=exception.getBindingResult();
            return ResultUtil.error(-99,
                    "参数:["+result.getFieldError().getField()+"]错误," +result.getFieldError().getDefaultMessage());
        }else if(e instanceof NoHandlerFoundException){
            String url=((NoHandlerFoundException) e).getRequestURL();

            return ResultUtil.error(-99,"未知的接口:"+url);
        }else if(e instanceof HttpRequestMethodNotSupportedException){
            String method=((HttpRequestMethodNotSupportedException) e).getMethod();

            return ResultUtil.error(-99,"请求方式不正确:"+method);
        }else {
            logger.error("[系统异常]",e.getMessage());
            return ResultUtil.error(-1,"[系统异常]");
        }
    }
}
