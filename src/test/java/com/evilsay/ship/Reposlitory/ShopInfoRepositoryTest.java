package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.ShopInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopInfoRepositoryTest {
    @Autowired
    private ShopInfoRepository shopInfoRepository;
    @Test
    public void findByOpenid() {
        ShopInfo byOpenid = shopInfoRepository.findByOpenid("123");
        Assert.assertNotEquals(0,byOpenid.getOpenid());
    }

    @Test
    public void findByShopStatus() {
        List<ShopInfo> byShopStatus = shopInfoRepository.findByShopStatus(0);
        Assert.assertNotEquals(0,byShopStatus.size());
    }
    @Test
    @Transactional
    public void addShopInfo(){
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setShopId(2);
        shopInfo.setShopType(1);
        shopInfo.setShopName("123");
        shopInfo.setShopFounder("123");
        shopInfo.setShopDescription("123");
        shopInfo.setShopPhone("213");
        shopInfo.setOpenid("123");
        shopInfo.setShopImg("123");
        shopInfo.setShopStatus(1);
        shopInfo.setShopCategoryType(2);
        ShopInfo save = shopInfoRepository.save(shopInfo);
    }

}