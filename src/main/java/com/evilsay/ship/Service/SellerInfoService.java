package com.evilsay.ship.Service;

import com.evilsay.ship.DataObject.SellerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: EvilSay
 * @Date: 2019/2/7 16:25
 */
public interface SellerInfoService {
    SellerInfo findBySellerOpenId(String openId);

    SellerInfo findByUserName(String username);

    SellerInfo findById(Integer id);

    Page<SellerInfo> findAll(Pageable pageable);

    void deleteByName(String username);

    SellerInfo save(SellerInfo sellerInfo);

    SellerInfo findByShop(Integer shopType);

    void deleteByShopType(Integer shopType);
}
