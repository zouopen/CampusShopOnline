package com.evilsay.ship.DataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ShopCategory {
    @Id
    @GeneratedValue
    private Integer shopCategoryId;
    //店铺类目名字
    private String shopCategoryName;
    //店铺类目编号
    private Integer shopCategoryType;
    //创建时间
    @CreatedDate
    private Date createTime;
    //更新时间
    @LastModifiedDate
    private Date updateTime;

    public ShopCategory() {
    }

    public ShopCategory(String shopCategoryName, Integer shopCategoryType) {
        this.shopCategoryName = shopCategoryName;
        this.shopCategoryType = shopCategoryType;
    }
}
