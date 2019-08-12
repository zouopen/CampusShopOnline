package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository infoRepository;
    @Test
    public void findByOpenid() {
        SellerInfo byOpenid = infoRepository.findByOpenid("1");
        Assert.assertNotNull(byOpenid);
    }
    @Test
    public void addSellerInfo(){

    }
}