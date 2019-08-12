package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;
    @Test
    public void findByOrderId() {
        List<OrderDetail> byOrderId = repository.findByOrderId("2");
        Assert.assertNotEquals(0,byOrderId.size());
    }

    @Test
    public void addOrderDetail(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("2");
        orderDetail.setShopType(1);
        orderDetail.setProductId("123");
        orderDetail.setProductPrice(new BigDecimal(1));
        orderDetail.setProductName("123");
        orderDetail.setProductQuantity(1);
        repository.save(orderDetail);
    }
}