package com.evilsay.ship.VO.ProductInfoVO;

import com.evilsay.ship.VO.ProductInfoVO.ProductInfoVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**商品类目详情
 * @Author: EvilSay
 * @Date: 2019/1/21 23:44
 */
@Data
public class ProductVO implements Serializable {


    private static final long serialVersionUID = 7091379727638482726L;
    @JsonProperty("categoryname")
    private String categoryName;
    @JsonProperty("categorytype")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
