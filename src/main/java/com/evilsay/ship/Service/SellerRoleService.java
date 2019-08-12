package com.evilsay.ship.Service;


import com.evilsay.ship.DataObject.SellerRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/2/12 17:59
 */
public interface SellerRoleService {

    SellerRole findOne(Integer id);

    Page<SellerRole> findAll(Pageable pageable);

    SellerRole save(SellerRole sellerRole);

    void deleteById(Integer id);

    SellerRole findByName(String name);

    SellerRole findByValue(String value);


    List<SellerRole> findAll();
}
