package com.evilsay.ship.DataObject;

import com.evilsay.ship.Enums.ShopStatusEnum;
import com.evilsay.ship.Enums.ShopToExamineEnum;
import com.evilsay.ship.Utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ShopInfo implements Serializable {

    private static final long serialVersionUID = -8631734905822639509L;

    @Id
    @GeneratedValue
    private Integer shopId;
    //店铺编号
    private Integer shopType;
    //店铺名称
    private String shopName;
    //店铺创建者
    private String shopFounder;
    //店铺描述
    private String shopDescription;
    //创建者电话号码
    private String shopPhone;
    //微信openid
    private String openid;
    //店铺图片
    private String shopImg;
    //商店状态,0营业时间1非营业时间
    private Integer shopStatus = ShopStatusEnum.ONLINE.getCode();
    //店铺类目编号
    private Integer shopCategoryType;
    //创建时间
    @CreatedDate
    private Date createTime;
    //更新时间
    @LastModifiedDate
    private Date updateTime;
    @JsonIgnore
    public ShopStatusEnum shopStatusEnum(){ return EnumUtils.getByCode(shopStatus,ShopStatusEnum.class); }
}
