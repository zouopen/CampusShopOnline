package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.SellerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,Integer> {

    SellerInfo findByOpenid (String openid);

    SellerInfo findByUsername (String username);

    Page<SellerInfo> findAll(Pageable pageable);

    @Modifying
    @Transactional
    void deleteByUsername(String userName);
    @Modifying
    @Transactional
    void deleteByShopType(Integer shopType);

    SellerInfo findByShopType (Integer shopType);
}
