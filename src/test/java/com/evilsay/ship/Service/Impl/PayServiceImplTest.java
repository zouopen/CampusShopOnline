package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.Service.OrderDTOService;
import com.evilsay.ship.Service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author: EvilSay
 * @Date: 2019/1/29 18:59
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class PayServiceImplTest {
    @Autowired
    private PayService service;
    @Autowired
    private OrderDTOService orderDTOService;
    @Test
    public void create()  {
        OrderDTO oneDetail = orderDTOService.findOneDetail("1548773630655904323");
        service.create(oneDetail);
    }
}