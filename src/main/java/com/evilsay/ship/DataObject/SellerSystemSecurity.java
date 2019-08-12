package com.evilsay.ship.DataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: EvilSay
 * @Date: 2019/3/7 8:47
 */
@Entity
@DynamicUpdate
//@DynamicInsert
@Data
@EntityListeners(AuditingEntityListener.class)
public class SellerSystemSecurity {
    @Id
    private String ipId;
//    非法IP
    private String sellerIp;
//    非法IP访问次数
    private Integer sellerRepeat = 1;
//    非法登录者
    private String sellerUser;
//    IP创建时间
    private String failureCause;
    @CreatedDate
    private Date createTime;
}
