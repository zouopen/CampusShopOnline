package com.evilsay.ship.VO;

import lombok.Data;

import java.io.Serializable;

/** http请求返回的商品对象
 * @Author: EvilSay
 * @Date: 2019/1/21 23:28
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 324933513224799226L;

    /** 错误码 **/
    private Integer code;
    /** 提示消息 **/
    private String  message;
    /** 返回的内容 **/
    private T data;

}
