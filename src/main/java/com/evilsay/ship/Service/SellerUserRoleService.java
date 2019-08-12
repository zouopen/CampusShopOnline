package com.evilsay.ship.Service;

import com.evilsay.ship.DataObject.SellerUserRole;

/**
 * @Author: EvilSay
 * @Date: 2019/2/17 19:18
 */
public interface SellerUserRoleService {

    SellerUserRole findByUserId(Integer id);

    SellerUserRole save(SellerUserRole sellerUserRole);

    void deleteByUserId(Integer id);
}
