package com.evilsay.ship.Enums;

import lombok.Getter;

/** 前端异常提醒
 * @Author: EvilSay
 * @Date: 2019/1/23 22:03
 */
@Getter
public enum  ResultEnum {
    ERROR(-1,""),
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态异常"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    CART_EMPTY(18,"购物车不能为空"),
    OPENID_ERROR(19,"openId异常你无权查看该订单详情"),
    OPENID_CANCEL_ERROR(19,"openId异常你无权取消该订单"),
    WX_MP_ERROR(20,"微信公众账号异常"),
    PAY_NOTIFY_MONEY_VERIFY_E(21,"微信支付异步通知金额错误"),
    PRODUCT_OFF_ERROR(22,"商品已下架"),
    PRODUCT_NOT_FIND(23,"在店铺中查无此商品"),
    WX_LOGIN_ERROR(24,"微信第三方登录异常"),
    PHONE_ERROR(25,"电话号码异常，非正常电话号码"),
    SEARCH_ERROR(26,"搜索字段或内容异常"),
    TO_EXAMINE_ERROR(27,"店铺正在审核"),
    APPLY_SHOP_SUCCESS(28,"店铺申请成功,请耐心等待审核！"),
    SHOP_TYPE_ERROR(29,"权限异常你无权查看该订单详情"),
    SHOP_NAME_REPLACE_ERROR(30,"店铺名重复！！"),
    SHOP_CATEGORY_NOT_EXITS(31,"店铺类目不存在或该类目没有店铺")
    ;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String  message;
}
