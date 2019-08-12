package com.evilsay.ship.DTO;

import lombok.Data;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

/**
 * @Author: EvilSay
 * @Date: 2019/2/12 17:41
 */
@Data
public class ShiroUserDTO implements Serializable,Principal {

    private static final long serialVersionUID = -1711865411721387273L;

    private Integer id;

    private Integer shopType;

    private String role;

    private String username;

    private Date createTime;

    @Override
    public String getName() {
        return null;
    }
}
