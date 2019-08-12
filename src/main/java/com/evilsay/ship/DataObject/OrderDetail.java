package com.evilsay.ship.DataObject;

import com.evilsay.ship.Utils.DataUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail {
    @Id
    private String detailId;
    //
    private String orderId;
    //店铺编号
    private Integer shopType;
    //
    private String productId;
    //商品名称
    private String productName;
    //当前价格,单位分
    private BigDecimal productPrice;
    //商品折扣
    private BigDecimal productDiscount;
    // 数量
    private Integer productQuantity;
    // 小图
    private String  productIcon;

    //创建时间
    @CreatedDate
    @JsonSerialize(using = DataUtils.class)
    private Date createTime;
    //更新时间
    @LastModifiedDate
    @JsonSerialize(using = DataUtils.class)
    private Date updateTime;
}
