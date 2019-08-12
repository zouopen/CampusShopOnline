package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ProductInfo;
import com.evilsay.ship.DataObject.ShopInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/21 0:41
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoServiceImpl service;
    @Test
    public void findOne() {
        ProductInfo one = service.findOne("123");
        Assert.assertEquals("123",one.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = service.findUpAll(1);
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void on_sale() {
        ProductInfo productInfo = service.off_save("1", 1);
        Assert.assertTrue("[上架订单]",productInfo.getProductStatus() == 1);
    }

    @Test
    public void findAll() {

    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("3");
        productInfo.setProductName("123");
        productInfo.setProductPrice(new BigDecimal(1));
        productInfo.setProductStock(1);
        productInfo.setProductDescription("123");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(1);
        productInfo.setShopType(1);
        ProductInfo save = service.save(productInfo);
        Assert.assertNotNull(save);
    }
    @Test
    public void findShopTypeANDProduct(){

        Assert.assertNotNull(service.findShopTypeANDProduct(1,"3"));
    }

}