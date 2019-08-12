package com.evilsay.ship.DataObject;

import com.evilsay.ship.Enums.OrderMasterStatusEnum;
import com.evilsay.ship.Enums.PayStatusEnum;
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
public class OrderMaster {
    @Id
    private String orderId;

    private Integer shopType;
    //买家名字
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态, 默认为新下单
    private Integer orderStatus = OrderMasterStatusEnum.NEW.getCode();
    //支付状态, 默认未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    //创建时间
    @CreatedDate
    private Date createTime;
    //更新时间
    @LastModifiedDate
    private Date updateTime;
}
