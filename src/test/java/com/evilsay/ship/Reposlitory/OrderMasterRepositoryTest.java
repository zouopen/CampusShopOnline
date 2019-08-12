package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository masterRepository;
    @Test
    public void addMaster(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1");
        orderMaster.setShopType(1);
        orderMaster.setBuyerName("123");
        orderMaster.setBuyerPhone("123");
        orderMaster.setBuyerAddress("123");
        orderMaster.setBuyerOpenid("123");
        orderMaster.setOrderAmount(new BigDecimal(1));
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(0);
        masterRepository.save(orderMaster);
    }
    @Test
    public void findByBuyerOpenid() {
        Page<OrderMaster> byBuyerOpenid = masterRepository.findByBuyerOpenid("123",new PageRequest(0,1));
        Assert.assertNotEquals(0,byBuyerOpenid.getTotalElements());
    }
    @Test
    public void test1(){
        OrderMaster ew3euwhd7sjw9diwkq = masterRepository.findByBuyerOpenid("ew3euwhd7sjw9diwkq");
        log.info(ew3euwhd7sjw9diwkq.toString());
    }
    @Test
    public void test2(){
       masterRepository.deleteByBuyerOpenid("ew3euwhd7sjw9diwkq");
    }
}