package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
//    @Query("select * FROM ProductCategory WHERE ProductCategoryType IN (1,3)")
    //根据Type查找归属的类目
    List<ProductCategory>findByCategoryTypeIn(List<Integer>categoryTypeList);
    //根据shopType查找归属的店铺
    List<ProductCategory>findByShopType(Integer shopType);
}
