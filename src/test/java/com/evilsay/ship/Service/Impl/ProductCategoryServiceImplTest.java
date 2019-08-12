package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/21 0:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryServiceImpl categoryService;
    @Test
    public void finOne() {
        ProductCategory productCategory = categoryService.finOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> ByShopTypeIn = categoryService.findByShopType(1);
        Assert.assertNotEquals(0,ByShopTypeIn.size());
    }

    @Test
    public void findByShopTypeIn() {
        List<ProductCategory> byCategoryTypeIn = categoryService.findByCategoryType(Arrays.asList(1, 2));
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = categoryService.finOne(8);
        productCategory.setCategoryType(2);
        productCategory.setCategoryId(8);
        productCategory.setCategoryName("1111");
        productCategory.setShopType(3);
        categoryService.update(productCategory);
    }
}