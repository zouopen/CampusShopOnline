package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.Reposlitory.SellerInfoRepository;
import com.evilsay.ship.Service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: EvilSay
 * @Date: 2019/2/7 16:26
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findBySellerOpenId(String openId) {
        return repository.findByOpenid(openId);
    }

    @Override
    public SellerInfo findByUserName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public SellerInfo findById(Integer id) { return repository.findOne(id); }

    @Override
    public Page<SellerInfo> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }


    @Override
    public void deleteByName(String username) {
        repository.deleteByUsername(username);
    }

    @Transactional
    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return repository.save(sellerInfo);
    }

    @Override
    public SellerInfo findByShop(Integer shopType) {
        return repository.findByShopType(shopType);
    }
    @Modifying
    @Transactional
    @Override
    public void deleteByShopType(Integer shopType) { repository.deleteByShopType(shopType); }

}
