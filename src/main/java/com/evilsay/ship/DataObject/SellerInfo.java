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
public class SellerInfo {
    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String password;
    //店铺编号
    private Integer shopType;
    //加盐
    private String salt;

    private Integer status;
    //卖家微信openid
    private String openid;
    //创建时间
    @CreatedDate
    private Date createTime;
    //更新时间
    @LastModifiedDate
    private Date updateTime;

}
