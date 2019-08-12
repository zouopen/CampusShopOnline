package com.evilsay.ship.Service;

import com.evilsay.ship.DTO.CartDTO;
import com.evilsay.ship.DataObject.ProductInfo;
import com.evilsay.ship.DataObject.ShopInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品信息
 * @Author: EvilSay
 * @Date: 2019/1/21 0:22
 */
public interface ProductInfoService {
    //前端API显示接口

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll(Integer shopType);



    //后端接口

    //根据shopType查找商品
    Page<ProductInfo> findByShopType(Pageable pageable, Integer shopType);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo on_save(String id, Integer shopType);

    //下架
    ProductInfo off_save(String id, Integer shopType);

    //根据店铺编号和商品ID查找商品详情(校验)
    ProductInfo findShopTypeANDProduct(Integer shopType,String id);

    Page<ProductInfo> findByProductNameLike(String name,Pageable pageable);

}
