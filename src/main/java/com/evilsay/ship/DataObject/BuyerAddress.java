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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/26 14:44
 * 4
 */
@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class BuyerAddress {

    @Id
    @GeneratedValue
    private Integer id;

    //买家微信Openid
    private String buyerOpenid;

    //买家名字
    private String buyerName;

    //买家电话号码
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //创建时间
    @CreatedDate
    @JsonSerialize(using = DataUtils.class)
    private Date createTime;

    //更新时间
    @LastModifiedDate
    @JsonSerialize(using = DataUtils.class)
    private Date updateTime;
}
