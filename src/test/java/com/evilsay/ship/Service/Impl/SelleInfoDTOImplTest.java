package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.Service.SelleInfoDTOService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author: EvilSay
 * @Date: 2019/2/18 23:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SelleInfoDTOImplTest {
    @Autowired
    private SelleInfoDTOService service;
    @Test
    public void findList() {
//        PageRequest pageRequest = new PageRequest(0,4);
//        Assert.assertNotEquals(0,service.findList(pageRequest).getTotalPages());
    }
}