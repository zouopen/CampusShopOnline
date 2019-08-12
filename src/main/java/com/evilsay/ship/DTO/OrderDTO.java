package com.evilsay.ship.DTO;

import com.evilsay.ship.DataObject.OrderDetail;
import com.evilsay.ship.Enums.OrderMasterStatusEnum;
import com.evilsay.ship.Enums.PayStatusEnum;
import com.evilsay.ship.Utils.DataUtils;
import com.evilsay.ship.Utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.xml.ws.Service;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 21:18
 */
@Data
public class OrderDTO {
    private String orderId;
    //店铺编号
    private Integer shopType;
    //商店名称
    private String shopName;
    //商店图片
    private String shopImg;
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
    private Integer orderStatus;
    //支付状态, 默认未支付
    private Integer payStatus;
    //创建时间
    @JsonSerialize(using = DataUtils.class)
    private Date createTime;
    //更新时间
    @JsonSerialize(using = DataUtils.class)
    private Date updateTime;
    //订单详细信息
    List<OrderDetail> orderDetailList;
    //在回传JSON中忽略该类的的回传
    @JsonIgnore
    public OrderMasterStatusEnum OrderMasterStatusEnum(){
        return EnumUtils.getByCode(orderStatus,OrderMasterStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum PayStatusEnum(){
        return EnumUtils.getByCode(payStatus,PayStatusEnum.class);
    }
}
