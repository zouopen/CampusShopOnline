package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/22 2:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopCategoryServiceImplTest {
    @Autowired
    private ShopCategoryServiceImpl categoryService ;
    @Test
    public void findOne() {
        ShopCategory shopCategory = categoryService.finOne(1);
        Assert.assertEquals(new Integer(1),shopCategory.getShopCategoryId());
    }
    @Test
    public void findByShopCategoryTypeList(){
        List<ShopCategory> byShopCategoryTypeList = categoryService.findByShopCategoryTypeList(Arrays.asList(1, 3));
        Assert.assertNotEquals(0,byShopCategoryTypeList.size());
    }
    @Test
    public void findUpAll() {
        List<ShopCategory> all = categoryService.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findAll() {
        List<ShopCategory> all = categoryService.findAll();
        Assert.assertNotEquals(0,all.size());
    }
    @Transactional
    @Test
    public void save() {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryName("汉堡");
        shopCategory.setShopCategoryType(2);
        ShopCategory save = categoryService.save(shopCategory);
        Assert.assertNotNull(save);
    }
}