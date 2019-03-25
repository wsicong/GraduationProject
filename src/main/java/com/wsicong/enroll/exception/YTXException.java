package com.wsicong.enroll.exception;

import com.wsicong.enroll.enums.YTXExceptionType;

public class YTXException extends RuntimeException {
    private Integer code;

    public YTXException(YTXExceptionType exceptionType){
        super(exceptionType.getMsg());
        this.code=exceptionType.getCode();
    }

    public YTXException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public YTXException(YTXExceptionType exceptionType, String msg){
        super(msg);
        this.code = exceptionType.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
