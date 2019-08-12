package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.ShopCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopCategoryRepository extends JpaRepository<ShopCategory,Integer> {
    //查找当前类目数值
//    @Query("select * FROM ShopCategory WHERE shopCategoryType IN (1,3)")
      List<ShopCategory> findByShopCategoryTypeIn (List<Integer>shopCategoryList);

      List<ShopCategory> findAll();

      ShopCategory findByShopCategoryType(Integer shopCategoryType);

      ShopCategory findByShopCategoryName(String name);
}
