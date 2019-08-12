package com.evilsay.ship.Service;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.DataObject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 21:14
 */
public interface OrderDTOService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);

    //查询订单详情
    OrderDTO findOneDetail(String orderId);

    //查询订单列表
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    //查询指定店铺订单列表
    Page<OrderDTO> findShopList(Integer shopType,Pageable pageable);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //支付订单
    OrderDTO payId(OrderDTO orderDTO);

    //查询店铺所已拥有的订单数量和详情
    List<OrderDTO> findShopOrderList(Integer shopType);

    //完结订单
    OrderDTO finish(OrderDTO orderDTO);

    //删除订单
    void delete(OrderDTO orderDTO);

    String getTotalSum(Integer shopType);
}
