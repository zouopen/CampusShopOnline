package com.evilsay.ship.Service;

import com.evilsay.ship.DataObject.ShopInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/21 0:59
 */
public interface ShopInfoService {

    ShopInfo findOne(Integer shopId);

    List<ShopInfo> findUpAll ();

    Page<ShopInfo> findCategoryType(Integer shopCategoryType,Pageable pageable);

    PageInfo<ShopInfo> findAll (Integer pageNum, Integer pageSize);
    @Transactional
    ShopInfo save (ShopInfo shopInfo);

    ShopInfo findByShopType(Integer shopType);

    void closeShop(Integer shopType);

    void OpenShop(Integer shopType);

    void deleteById(Integer id);

    Page<ShopInfo> findByShopNameLike(String name,Pageable pageable);

    ShopInfo findByShopName(String shopName);
}
