package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ShopInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/21 1:07
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ShopInfoServiceImplTest {
    @Autowired
    private ShopInfoServiceImpl service;
    @Test
    public void findOne() {
        ShopInfo one = service.findOne(5);
        Assert.assertNotNull("123",one.getShopId());
    }

    @Test
    public void findUpAll() {
        List<ShopInfo> upAll = service.findUpAll();
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void findAll() {

    }

    @Test
    public void save() {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopId(4);
        shopInfo.setShopType(4);
        shopInfo.setShopName("Test");
        shopInfo.setShopFounder("test");
        shopInfo.setShopDescription("123456");
        shopInfo.setShopPhone("110");
        shopInfo.setOpenid("abc");
        shopInfo.setShopImg("http://xxxx.com");
        shopInfo.setShopStatus(0);
        shopInfo.setShopCategoryType(1);
        service.save(shopInfo);
    }

    @Test
    public void findByShopNameLike() {
        PageRequest pageRequest =  new PageRequest(0,1);
        Page<ShopInfo> shopNameLike = service.findByShopNameLike("t", pageRequest);
        System.out.println(shopNameLike.getTotalPages());
    }

    @Test
    public void findCategoryType() {
        PageRequest pageRequest =  new PageRequest(0,1);
        Page<ShopInfo> categoryType = service.findCategoryType(2, pageRequest);
        System.out.println(categoryType.getContent().size());
        Assert.assertNotEquals(null,categoryType);

    }

}