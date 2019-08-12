package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.SellerSystemSecurity;
import com.evilsay.ship.Service.SellerSystemSecurityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: EvilSay
 * @Date: 2019/3/7 9:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerSystemSecurityServiceImplTest {
    @Autowired
    private SellerSystemSecurityService service;
    @Test
    public void findOne() {

    }

    @Test
    public void save() {
        SellerSystemSecurity sellerSystemSecurity = new SellerSystemSecurity();
        sellerSystemSecurity.setSellerIp("192.168.1.1");
        sellerSystemSecurity.setIpId("1");
        service.save(sellerSystemSecurity);
    }
}