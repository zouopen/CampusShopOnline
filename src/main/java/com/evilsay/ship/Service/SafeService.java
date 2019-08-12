package com.evilsay.ship.Service;

import com.evilsay.ship.DTO.OrderDTO;

import java.util.List;

/** 校验openid信息
 * @Author: EvilSay
 * @Date: 2019/1/27 22:02
 */
public interface SafeService {

    OrderDTO finOrderOne(String openid,String orderId);

    List<OrderDTO> findOrderShopOne(String openid, Integer type);

    OrderDTO cancelOrder(String openid,String orderId);

    List<OrderDTO> findMobileOrderList(Integer shopType);

    OrderDTO findMobileOrderOne(Integer shopType,String orderId);
}
