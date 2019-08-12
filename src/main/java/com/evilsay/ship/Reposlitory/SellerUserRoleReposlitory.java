package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.SellerUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: EvilSay
 * @Date: 2019/2/17 19:16
 */
public interface SellerUserRoleReposlitory extends JpaRepository<SellerUserRole,Integer> {

    SellerUserRole findByUserId (Integer id);


    @Modifying
    @Transactional
    void deleteByUserId(Integer userId);
}
