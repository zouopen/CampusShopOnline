package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.Security.ShiroKit;
import com.evilsay.ship.Service.SellerInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: EvilSay
 * @Date: 2019/2/16 23:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoServiceImplTest {
    @Autowired
    private SellerInfoService service;
    @Test
    public void findBySellerOpenId() {
    }

    @Test
    public void findByUserName() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        String salt = ShiroKit.getRandomSalt(5);
        String saltPwd = ShiroKit.md5("zoupeng..123",salt);
        sellerInfo.setUsername("111122");
        sellerInfo.setPassword(saltPwd);
        sellerInfo.setSalt(salt);
        sellerInfo.setStatus(1);
        service.save(sellerInfo);
    }
}