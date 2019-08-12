package com.evilsay.ship.Converter;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.DataObject.OrderDetail;
import com.evilsay.ship.DataObject.OrderMaster;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Reposlitory.OrderDetailRepository;
import com.evilsay.ship.Service.ShopInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/** 属性copy
 * @Author: EvilSay
 * @Date: 2019/1/25 0:02
 */
@Component
public class OrderMaster2OrderDTOConverter {

    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private OrderDetailRepository detailRepository;

    private OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();

        BeanUtils.copyProperties(orderMaster,orderDTO);
        //查询店铺名称及图片
        ShopInfo shopInfo = shopInfoService.findByShopType(orderMaster.getShopType());

        orderDTO.setShopName(shopInfo.getShopName());

        orderDTO.setShopImg(shopInfo.getShopImg());

        List<OrderDetail> orderDetails = detailRepository.findByOrderId(orderMaster.getOrderId());

        orderDTO.setOrderDetailList(orderDetails);

        return orderDTO;
    }
    public  List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
