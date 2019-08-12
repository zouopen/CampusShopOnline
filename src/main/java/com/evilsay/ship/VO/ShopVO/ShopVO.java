package com.evilsay.ship.VO.ShopVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/22 13:41
 */
@Data
public class ShopVO implements Serializable {


    private static final long serialVersionUID = -486142867683786239L;
    @JsonProperty("name")
    private String shopCategoryName;

    @JsonProperty("categorytype")
    private Integer shopCategoryType;

    @JsonProperty("shops")
    private List<ShopInfoVO> shopInfoVOList;

}
