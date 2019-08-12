package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.SellerRole;
import com.evilsay.ship.Reposlitory.SellerRoleRepository;
import com.evilsay.ship.Service.SellerRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**角色控制
 * @Author: EvilSay
 * @Date: 2019/2/12 17:59
 */
@Service
public class SellerRoleServiceImpl implements SellerRoleService {
    @Autowired
    private SellerRoleRepository roleRepository;
    @Override
    public SellerRole findOne(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Page<SellerRole> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
    @Override
    public SellerRole save(SellerRole sellerRole) {
        return roleRepository.save(sellerRole);
    }

    @Override
    public void deleteById(Integer id) {
        roleRepository.deleteById(id);
    }
    @Override
    public SellerRole findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public SellerRole findByValue(String value) {
        return roleRepository.findByValue(value);
    }

    @Override
    public List<SellerRole> findAll() {
        return roleRepository.findAll();
    }
}
