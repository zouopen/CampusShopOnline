package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.ShopInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopInfoRepository extends JpaRepository<ShopInfo,Integer> {

    ShopInfo findByOpenid (String openId);


    List<ShopInfo> findByShopStatus (Integer shopStatus);

    ShopInfo findByShopType (Integer shopType);

    Page<ShopInfo> findByShopStatusAndShopCategoryType(Integer shopStatus,Integer shopCategoryType,Pageable pageable);

    @Query("select s from ShopInfo s where s.shopName like %?1%")
    Page<ShopInfo> findByShopNameLike (String name,Pageable pageable);

    List<ShopInfo> findAll ();

    ShopInfo findByShopName (String shopName);

}
