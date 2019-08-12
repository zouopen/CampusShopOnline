package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.DataObject.OrderDetail;
import com.evilsay.ship.Enums.OrderMasterStatusEnum;
import com.evilsay.ship.Enums.PayStatusEnum;
import com.evilsay.ship.Service.OrderDTOService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 23:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDTOServiceImplTest {
    @Autowired
    private OrderDTOService dtoService;

    private static final String OPEN_ID = "ew3euwhd7sjw9diwkq";

    private static final String ORDER_ID = "1548260099542399286";
    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("鑫辉");
        orderDTO.setBuyerAddress("地标");
        orderDTO.setBuyerPhone("123");
        orderDTO.setBuyerOpenid(OPEN_ID);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("2");
        orderDetail.setProductQuantity(2);
        orderDetail.setShopType(2);
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("1");
        orderDetail1.setProductQuantity(2);
        orderDetail1.setShopType(2);
        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO orderDTO1 = dtoService.create(orderDTO);
        log.info("[创建订单] result={}",orderDTO1);
    }

    @Test
    public void findOne() {
        OrderDTO one = dtoService.findOneDetail(ORDER_ID);
        log.info("[查询单个订单] one= {}",one);
        Assert.assertEquals(ORDER_ID,one.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> list = dtoService.findList(OPEN_ID, request);
        Assert.assertNotEquals(0,list.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO one = dtoService.findOneDetail(ORDER_ID);
        OrderDTO cancel = dtoService.cancel(one);
        Assert.assertEquals(cancel.getOrderStatus(),OrderMasterStatusEnum.CANCEL.getCode());
    }

    @Test
    public void payId() {
        OrderDTO one = dtoService.findOneDetail(ORDER_ID);
        OrderDTO pay = dtoService.payId(one);
        Assert.assertEquals(pay.getPayStatus(),PayStatusEnum.SUCCESS.getCode());
    }

    @Test
    public void findShop() {
        List<OrderDTO> shopOrderList = dtoService.findShopOrderList(1);
        for (OrderDTO orderDTO : shopOrderList){
            log.info("[查询店铺订单]"+orderDTO.getOrderAmount());
//            log.info("[查询店铺订单详情]"+orderDTO.getOrderDetailList());
        }
        log.info("[查询店铺拥有的订单]"+shopOrderList.size());
    }
    @Test
    public void finish(){
        OrderDTO one = dtoService.findOneDetail(ORDER_ID);
        OrderDTO finish = dtoService.finish(one);
        Assert.assertEquals(OrderMasterStatusEnum.FINISHED.getCode(),finish.getOrderStatus());
    }
    @Test
    public void delete(){
        OrderDTO oneDetail = dtoService.findOneDetail("1548504304630540445");
        dtoService.delete(oneDetail);
    }
    @Test
    public void getTotalSum(){
//        String totalSum = dtoService.getTotalSum(2);
//        log.info("totalSum"+totalSum);
    }
}