package com.wsicong.enroll.enums;

/**
 * 错误类定义
 */
public enum YTXExceptionType {
    CAN_NOT_FIND_VALID_CODE(-1,"未找到验证码"),
    MOBILE_INCORRECTNESS(-2,"手机号不正确"),
    VALID_CODE_INCORRECTNESS(-3,"验证码不正确"),
    USER_UNKNOWN(-4,"未知用户"),
    USER_INCORRECT_PASSWORD(-5,"密码不正确"),
    USER_ALREADY_EXIST(-6,"用户已存在"),
    DELETE_FAIL(-7,"删除失败,id不正确"),
    ROLE_ALREADY_EXIST(-8,"角色code已存在"),
    ROLE_HAS_ADMIN_USER(-9,"当前角色下含有用户无法删除"),
    ROLE_NOT_FOUND(-10,"角色不存在"),
    MENU_ALREADY_EXIST(-11,"菜单code已存在"),
    MENU_DELETE_HAS_SUB(-12,"菜单包含子菜单,请先删除子菜单"),
    MENU_NOT_FOUND(-13,"菜单id不存在"),
    ROLE_MENU_ALREADY_EXIST(-14,"当前角色已存在该菜单");

    private Integer code;
    private String msg;

    YTXExceptionType(Integer code, String msg){
        this.code=code;
        this.msg=msg;
    }
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
