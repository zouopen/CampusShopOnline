package com.evilsay.ship.From;

import lombok.Data;

/**
 * @Author: EvilSay
 * @Date: 2019/2/5 0:38
 */
@Data
public class ProductCategoryFrom {
    private Integer categoryId;
    //类目名字
    private String categoryName;
    //类目编号
    private Integer categoryType;
    //店铺编号
    private Integer shopType;
    //创建时间
}
