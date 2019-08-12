package com.evilsay.ship.Service;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.DataObject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 21:14
 */
public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //查询订单
    OrderDTO findOne (String orderId);

    //查询订单列表
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //支付订单
    OrderDTO payId(OrderDTO orderDTO);

    OrderDTO findShop(String shopType);

}
