package com.evilsay.ship.From;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author: EvilSay
 * @Date: 2019/2/19 14:48
 */
@Data
public class UserFrom {

    private Integer userId;
    @NotEmpty(message = "名字不能为空")
    private String username;

    private String password;

    private String createBy;

    private Integer roleId;

}
