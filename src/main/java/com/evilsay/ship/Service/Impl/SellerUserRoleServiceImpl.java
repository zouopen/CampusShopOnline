package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.SellerUserRole;
import com.evilsay.ship.Reposlitory.SellerUserRoleReposlitory;
import com.evilsay.ship.Service.SellerUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: EvilSay
 * @Date: 2019/2/17 19:21
 */
@Service
public class SellerUserRoleServiceImpl implements SellerUserRoleService {
    @Autowired
    private SellerUserRoleReposlitory roleReposlitory;
    @Override
    public SellerUserRole findByUserId(Integer id) {
        return roleReposlitory.findByUserId(id);
    }
    @Transactional
    @Override
    public SellerUserRole save(SellerUserRole sellerUserRole) {
        return roleReposlitory.save(sellerUserRole);
    }

    @Override
    public void deleteByUserId(Integer id) {
        roleReposlitory.deleteByUserId(id);
    }

}
