package com.evilsay.ship.Service;

import com.evilsay.ship.DataObject.ShopCategory;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/22 2:41
 */
public interface ShopCategoryService {
    //查找单一类目
    ShopCategory finOne (Integer categoryId);
    //查找所有类目
    List<ShopCategory> findAll ();

    ShopCategory findByShopCategoryType(Integer shopCategoryType);

    PageInfo<ShopCategory> findAll(Integer pageNum, Integer pageSize);
    //根据shopType查找归属的店铺
    List<ShopCategory>findByShopCategoryTypeList(List<Integer>ShopCategoryTypeList);
    //保存
    ShopCategory save(ShopCategory productCategory);
    //根据类目Id删除类目
    ShopCategory findName(String name);
}
