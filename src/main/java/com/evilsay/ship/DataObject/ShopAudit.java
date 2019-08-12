package com.evilsay.ship.DataObject;

import com.evilsay.ship.Enums.ShopStatusEnum;
import com.evilsay.ship.Enums.ShopToExamineEnum;
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

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/21 12:46
 * 4
 */


@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ShopAudit {

    @Id
    @GeneratedValue
    private Integer auditId;
    //申请帐号
    private String username;
    //申请帐号密码
    private String password;
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
    //店铺类目编号
    private Integer shopCategoryType;
    //创建时间
    @CreatedDate
    private Date createTime;
    //更新时间
    @LastModifiedDate
    private Date updateTime;
}
