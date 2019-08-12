package com.evilsay.ship.Enums;

import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/2/10 17:39
 */
@Getter
public enum LoginStatusEnums implements CodeEnum {
    ROLE_STATUS(1,"可用"),
    ADMIN(2,"admin"),
    LOGIN_PARAM_ERROR(3,"登录参数不正确"),
    LOGIN_ROLE_ERROR(4,"越权！已记录IP"),
    LOGIN_NAME_ERROR(5,"账号重复,请重新申请"),
    USER_ERROR(6,"user"),
    LOGIN_ERROR(7,"您无权查看此信息"),
    LOGIN_INFO_ERROR(8,"您账号密码错误"),
    LOGIN_BLACK_ERROR(9,"你错误操作次数过多，请与平台工作人员联系"),
    ROLE_ERROR(10,"越权访问"),
    LOGIN_ERROR_MORE(11,"提交的太快啦,请休息一下吧"),
    LOGIN_BLACK_REPLACE(12,"密码错误次数太多了")
    ;

    LoginStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String  message;
}
