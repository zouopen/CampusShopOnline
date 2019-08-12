package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.ShopStatusEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Reposlitory.ShopInfoRepository;
import com.evilsay.ship.Service.ShopInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 店铺信息
 * @Author: EvilSay
 * @Date: 2019/1/21 1:02
 */

@Service
@CacheConfig(cacheNames = "shop")
public class ShopInfoServiceImpl implements ShopInfoService {
    @Autowired
    private ShopInfoRepository repository ;
    @Override

    public ShopInfo findOne(Integer shopId) {
        return repository.findOne(shopId);
    }

    @Override
    @Cacheable(key = "19596")
    public List<ShopInfo> findUpAll() {
        return repository.findByShopStatus(ShopStatusEnum.ONLINE.getCode());
    }

    @Override
    public PageInfo<ShopInfo> findAll(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }

        List<ShopInfo> all = repository.findAll();

        return new PageInfo<>(all);
    }

    @Override
    @CacheEvict(key = "19596")
    public ShopInfo save(ShopInfo shopInfo) {
        return repository.save(shopInfo);
    }

    @Override
    public Page<ShopInfo> findCategoryType(Integer shopCategoryType, Pageable pageable) {
        return repository.findByShopStatusAndShopCategoryType(ShopStatusEnum.ONLINE.getCode(),shopCategoryType,pageable);
    }

    @Override
    public ShopInfo findByShopType(Integer shopType) {
        return repository.findByShopType(shopType);
    }

    @Override
    public void closeShop(Integer shopType) {
        ShopInfo shopInfo = repository.findByShopType(shopType);
        if (!shopInfo.getShopStatus().equals(ShopStatusEnum.ONLINE.getCode())){
            throw new ShipException(ShopStatusEnum.ERROR_CLOSE_SHOP.getCode()
                    ,ShopStatusEnum.ERROR_CLOSE_SHOP.getMessage());
        }
        shopInfo.setShopStatus(ShopStatusEnum.OFFLINE.getCode());
        repository.save(shopInfo);
    }

    @Override
    public void OpenShop(Integer shopType) {
        ShopInfo shopInfo = repository.findByShopType(shopType);
        if (!shopInfo.getShopStatus().equals(ShopStatusEnum.OFFLINE.getCode())){
            throw new ShipException(ShopStatusEnum.ERROR_CLOSE_SHOP.getCode()
                    ,ShopStatusEnum.ERROR_CLOSE_SHOP.getMessage());
        }
        shopInfo.setShopStatus(ShopStatusEnum.ONLINE.getCode());
        repository.save(shopInfo);
    }
    @Modifying
    @Transactional
    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }

    @Override
    public Page<ShopInfo> findByShopNameLike(String name, Pageable pageable) {
        return repository.findByShopNameLike(name,pageable);
    }

    @Override
    public ShopInfo findByShopName(String shopName) { return repository.findByShopName(shopName); }

}
