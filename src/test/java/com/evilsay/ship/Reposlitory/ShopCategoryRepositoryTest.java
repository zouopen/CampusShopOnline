package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShopCategoryRepositoryTest {
    @Autowired
    private ShopCategoryRepository repository;
    @Test
    public void findByShopCategoryIdIn() {
        List<Integer> list = Arrays.asList(1,3);
        List<ShopCategory> byShopCategoryIdIn = repository.findByShopCategoryTypeIn(list);
        Assert.assertNotEquals(0,byShopCategoryIdIn);
    }
    @Test
    public void addShopCategory(){
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryName("汤粉");
        shopCategory.setShopCategoryType(2);
        ShopCategory save = repository.save(shopCategory);
        Assert.assertNotNull(save);
    }
}