package com.evilsay.ship.DataObject;

import com.evilsay.ship.Enums.ProductStatusEnum;
import com.evilsay.ship.Utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = 4407186277691788215L;
    @Id
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
    private Integer productStatus  = ProductStatusEnum.UP.getCode();
    //商品类目
    private Integer categoryType;
    //商品折扣
    private BigDecimal productDiscount;
    //归属店铺
    private Integer shopType;
    //创建时间
    @CreatedDate
    private Date createTime;
    //更新时间
    @LastModifiedDate
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum productStatusEnum() {
        return EnumUtils.getByCode(productStatus, ProductStatusEnum.class);
    }

}

