package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.SellerRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/** 角色控制类
 * @Author: EvilSay
 * @Date: 2019/2/12 17:55
 */
public interface SellerRoleRepository extends JpaRepository<SellerRole,Integer> {

    void deleteById(Integer id);

    Page<SellerRole> findAll(Pageable pageable);

    SellerRole findByName (String name);

    SellerRole findByValue (String value);
}
