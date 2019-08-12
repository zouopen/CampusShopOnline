package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ShopAudit;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Reposlitory.ShopAuditRepository;
import com.evilsay.ship.Service.ShopAuditService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/21 13:22
 * 4
 */
@Service
@Slf4j
public class ShopAuditServiceImpl implements ShopAuditService {

    @Autowired
    private ShopAuditRepository shopAuditRepository;

    @Override
    public ShopAudit findOne(Integer id) {
        return shopAuditRepository.findOne(id);
    }

    @Override
    public ShopAudit findByUsername(String username) {
        return shopAuditRepository.findByUsername(username);
    }

    @Override
    public PageInfo<ShopAudit> findAll(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<ShopAudit> all = shopAuditRepository.findAll();
        return new PageInfo<>(all);
    }

    @Override
    public ShopAudit save(ShopAudit shopAudit) {
        return shopAuditRepository.save(shopAudit);
    }

    @Modifying
    @Transactional
    @Override
    public void DeleteById(Integer id) {

        shopAuditRepository.delete(id);

    }
}
