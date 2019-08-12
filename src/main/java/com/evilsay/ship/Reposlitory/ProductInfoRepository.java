package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    //根据商品状态码显示商品
    List<ProductInfo> findByProductStatusAndShopType(Integer productStatus,Integer shopType);

    Page<ProductInfo> findByShopType(Integer shopType, Pageable pageable);

    ProductInfo findByShopTypeAndProductId(Integer shopType,String productId);

    @Query("select p from ProductInfo p where p.productName like %?1%")
    Page<ProductInfo> findByProductNameLike(String name,Pageable pageable);
}
