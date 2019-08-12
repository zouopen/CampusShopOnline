package com.evilsay.ship.Converter;

import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.From.OrderFrom;
import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.DataObject.OrderDetail;
import com.evilsay.ship.Exception.ShipException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/** 属性copy
 * @Author: EvilSay
 * @Date: 2019/1/25 19:08
 */
@Slf4j
public class OrderFrom2OrderDTOConverter {
    public static OrderDTO convert(OrderFrom orderFrom){
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList;
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderFrom.getName());
        orderDTO.setBuyerPhone(orderFrom.getPhone());
        orderDTO.setBuyerAddress(orderFrom.getAddress());
        orderDTO.setBuyerOpenid(orderFrom.getOpenid());
        try {
            orderDetailList = gson.fromJson(orderFrom.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("[对象转换]错误,string={}",orderFrom.getItems());
            throw new ShipException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
