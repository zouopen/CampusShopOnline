package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Enums.ResultShopEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.OrderDTOService;
import com.evilsay.ship.Service.SafeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/27 22:23
 */
@Service
@Slf4j
public class SafeServiceImpl implements SafeService {
    @Autowired
    private OrderDTOService orderDTOService;
    @Override
    public OrderDTO finOrderOne(String openid, String orderId) {

        return checkOrderSafe(openid,orderId);
    }
    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderSafe(openid, orderId);
        return orderDTOService.cancel(orderDTO);
    }

    @Override
    public List<OrderDTO> findMobileOrderList(Integer shopType) {
        List<OrderDTO> shopOrderList = orderDTOService.findShopOrderList(shopType);
        if (shopOrderList.isEmpty()) {
            throw new ShipException(ResultShopEnum.SHOP_NOT_EXITS);
        }
        return shopOrderList;
    }

    @Override
    public OrderDTO findMobileOrderOne(Integer shopType, String orderId) {
        OrderDTO oneDetail = orderDTOService.findOneDetail(orderId);
        if (oneDetail == null) {
            return null;
        }
        if (!oneDetail.getShopType().equals(shopType)){
            log.error("[查询订单]openid不一致,openid={} orderDto={}",shopType,oneDetail);
            throw new ShipException(ResultEnum.SHOP_TYPE_ERROR);
        }
        return oneDetail;
    }

    @Override
    public List<OrderDTO> findOrderShopOne(String openid, Integer type) {
        List<OrderDTO> shopOrderList = orderDTOService.findShopOrderList(type);
        for (OrderDTO orderDTO : shopOrderList){
            if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)){
                log.error("[查询店铺订单]openid不一致,openid={} orderDto={}",openid,orderDTO);
                throw new ShipException(ResultShopEnum.OPENID_ERROR);
            }
        }
        return shopOrderList;
    }
    private OrderDTO checkOrderSafe(String openid, String orderId){
        OrderDTO oneDetail = orderDTOService.findOneDetail(orderId);
        if (oneDetail == null){
            return null;
        }
        if (!oneDetail.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("[查询订单]openid不一致,openid={} orderDto={}",openid,oneDetail);
            throw new ShipException(ResultEnum.OPENID_ERROR);
        }
        return oneDetail;
    }

}
