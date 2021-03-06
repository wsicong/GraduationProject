package com.wsicong.enroll.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送短信验证码
 */
public class SendMsgServer {
    private static final Logger logger = LoggerFactory.getLogger(SendMsgServer.class);

    /**
     * (公共)发送短消息
     *
     * @param messageStr 发送的消息
     * @param phoneNum   发送的手机号
     * @return 发送成功返回:ok，发送失败返回:no
     */
    public static String SendMsg(String messageStr, String phoneNum) {
        //TODO
        logger.debug("发送信息成功");
        return "ok";
    }
}
