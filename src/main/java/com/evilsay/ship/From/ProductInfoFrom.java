package com.evilsay.ship.From;

import com.evilsay.ship.Enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: EvilSay
 * @Date: 2019/2/4 21:50
 */
@Data
public class ProductInfoFrom implements Serializable {



    private String productId;
    // 商品名称
    private String productName;
    // 商品价格
    private BigDecimal productPrice;
    // 商品库存
    private Integer productStock;
    // 商品描述
    private String productDescription;
    //商品图片
    private String productIcon;
    //商品状态
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    //商品类目
    private Integer categoryType;
    //商品折扣

    private BigDecimal productDiscount;
    //归属店铺
    private Integer shopType;
}
