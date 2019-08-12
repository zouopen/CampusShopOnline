package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;
    @Test
    public void findByProductStatus() {

    }
    @Test
    public void addProductInfo(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("123");
        productInfo.setProductPrice(new BigDecimal(1));
        productInfo.setProductStock(15);
        productInfo.setProductDescription("123");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(1);
        productInfo.setShopType(1);
        ProductInfo save = repository.save(productInfo);
        Assert.assertNotNull(save);

    }
}