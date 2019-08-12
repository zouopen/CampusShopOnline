package com.evilsay.ship.VO.ShopVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: EvilSay
 * @Date: 2019/1/22 13:49
 */
@Data
public class ShopInfoVO implements Serializable {


    private static final long serialVersionUID = 8345752490375022427L;
    @JsonProperty("shoptype")
    private Integer shopType;

    @JsonProperty("name")
    private String shopName;


    @JsonProperty("description")
    private String shopDescription;

    @JsonProperty("icon")
    private String shopImg;
}
