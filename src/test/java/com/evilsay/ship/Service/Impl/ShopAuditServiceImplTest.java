package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ShopAudit;
import com.evilsay.ship.Reposlitory.ShopAuditRepository;
import com.evilsay.ship.Service.ShopAuditService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/21 13:26
 * 4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ShopAuditServiceImplTest {

    @Autowired
    private ShopAuditServiceImpl shopAuditService;


    @Test
    public void findAll() {
        ShopAudit one = shopAuditService.findOne(13);
        System.out.println(one.getPassword());
    }

    @Test
    public void save() {
        ShopAudit shopAudit = new ShopAudit();
        shopAudit.setUsername("aaaa");
        shopAudit.setPassword("123");
        shopAudit.setShopName("sss");
        shopAudit.setShopCategoryType(1);
        shopAudit.setShopFounder("test");
        shopAudit.setShopDescription("123456");
        shopAudit.setShopPhone("110");
        shopAudit.setOpenid("abc");
        shopAudit.setShopImg("http://xxxx.com");
        ShopAudit shopAudit1 = shopAuditService.save(shopAudit);
        Assert.assertNotEquals(null,shopAudit1);
    }

    @Test
    public void deleteById() {
        shopAuditService.DeleteById(1);
    }

    @Test
    public void findByUsername() {
        ShopAudit byUsername = shopAuditService.findByUsername("aaaa");
        Assert.assertNotEquals(null,byUsername);

    }

    @Test
    public void findOne() {
        ShopAudit shopAudit = shopAuditService.findOne(2);
        Assert.assertNotEquals(null,shopAudit);
    }
}