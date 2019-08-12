package com.evilsay.ship.Service;

import com.evilsay.ship.DataObject.ShopAudit;

import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/21 13:20
 * 4
 */
public interface ShopAuditService {

    ShopAudit findOne(Integer id);

    ShopAudit findByUsername(String username);

    PageInfo<ShopAudit> findAll (Integer pageNum, Integer pageSize);

    @Transactional
    ShopAudit save (ShopAudit shopAudit);

    void DeleteById(Integer id);
}
