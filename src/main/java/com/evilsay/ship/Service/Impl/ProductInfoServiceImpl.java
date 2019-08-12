package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DTO.CartDTO;
import com.evilsay.ship.DataObject.ProductInfo;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.ProductStatusEnum;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Enums.ResultShopEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Reposlitory.ProductInfoRepository;
import com.evilsay.ship.Reposlitory.ShopInfoRepository;
import com.evilsay.ship.Service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/21 0:32
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "product")
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository repository;
    @Autowired
    private ShopInfoRepository infoRepository;

    @Override

    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    @Cacheable(key = "#shopType")
    public List<ProductInfo> findUpAll(Integer shopType) {
        return repository.findByProductStatusAndShopType(ProductStatusEnum.UP.getCode(),shopType);
    }

    @Override
    public Page<ProductInfo> findByShopType(Pageable pageable, Integer shopType) {
        return repository.findByShopType(shopType, pageable);
    }

    @Override
    @CacheEvict(key = "#productInfo.getShopType()")
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new ShipException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            ShopInfo shopType = infoRepository.findByShopType(cartDTO.getShopType());
            if (productInfo == null || shopType == null) {
                throw new ShipException(ResultShopEnum.SHOP_INFO_NOT_EXITS, ResultEnum.PRODUCT_STOCK_ERROR);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new ShipException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public ProductInfo on_save(String id, Integer shopType) {
        ProductInfo productInfo = findShopTypeANDProduct(shopType, id);
        if (productInfo.productStatusEnum().getMessage().equalsIgnoreCase(ProductStatusEnum.UP.getMessage())){
            throw new ShipException(ResultEnum.PRODUCT_OFF_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo off_save(String id, Integer shopType) {
        ProductInfo productInfo = findShopTypeANDProduct(shopType, id);
        if (productInfo.productStatusEnum().getMessage().equalsIgnoreCase(ProductStatusEnum.DOWN.getMessage())){
            throw new ShipException(ResultEnum.PRODUCT_OFF_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
    //校验商店中是否有该商品
    @Override
    public ProductInfo findShopTypeANDProduct(Integer shopType, String id) {
        ProductInfo productInfo = repository.findByShopTypeAndProductId(shopType, id);
        if (productInfo == null || !productInfo.getShopType().equals(shopType)){
            throw new ShipException(ResultEnum.PRODUCT_NOT_FIND);
        }
        return repository.findByShopTypeAndProductId(shopType,id);
    }

    @Override
    public Page<ProductInfo> findByProductNameLike(String name,Pageable pageable) {
        return repository.findByProductNameLike(name,pageable);
    }
}

