package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ShopCategory;
import com.evilsay.ship.Reposlitory.ShopCategoryRepository;
import com.evilsay.ship.Service.ShopCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/1/22 2:45
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryRepository categoryRepository;

    @Override
    public ShopCategory finOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<ShopCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public PageInfo<ShopCategory> findAll(Integer pageNum, Integer pageSize) {

        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<ShopCategory> all = categoryRepository.findAll();

        return new PageInfo<>(all);
    }

    @Override
    public List<ShopCategory> findByShopCategoryTypeList(List<Integer> ShopCategoryTypeList) {
        return categoryRepository.findByShopCategoryTypeIn(ShopCategoryTypeList);
    }

    @Override
    public ShopCategory findByShopCategoryType(Integer shopCategoryType) {
        return categoryRepository.findByShopCategoryType(shopCategoryType);
    }

    @Override
    public ShopCategory save(ShopCategory productCategory) {
        return categoryRepository.save(productCategory);
    }

    @Override
    public ShopCategory findName(String name) {
        return categoryRepository.findByShopCategoryName(name);
    }

}
