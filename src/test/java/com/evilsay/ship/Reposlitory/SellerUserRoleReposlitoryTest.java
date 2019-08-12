package com.evilsay.ship.Reposlitory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @Author: EvilSay
 * @Date: 2019/2/19 17:05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerUserRoleReposlitoryTest {
    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void deleteByUserId() {
        repository.deleteByUsername("1111");
    }
}