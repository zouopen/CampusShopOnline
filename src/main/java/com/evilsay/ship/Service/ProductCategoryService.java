package com.evilsay.ship.Service;

import com.evilsay.ship.DataObject.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    //查找单一类目
    ProductCategory finOne (Integer categoryId);
    //查找所有类目
    List<ProductCategory> findAll ();
    //根据Type查找归属的类目
    List<ProductCategory>findByCategoryTypeIn(List<Integer>categoryTypeList,Integer shopType);
    //根据shopType查找归属的店铺
    List<ProductCategory>findByShopType(Integer ShopType);
    //保存
    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory>findByCategoryType(List<Integer>categoryTypeList);

    ProductCategory update(ProductCategory productCategory);
}
