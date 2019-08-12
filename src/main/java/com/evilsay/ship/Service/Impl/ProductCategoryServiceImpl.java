package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.ProductCategory;
import com.evilsay.ship.Enums.ProductStatusEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Reposlitory.ProductCategoryRepository;
import com.evilsay.ship.Service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 类目
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository categoryRepository;
    @Override
    public ProductCategory finOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList,Integer shopType) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public List<ProductCategory> findByShopType(Integer shopType) {
        return categoryRepository.findByShopType(shopType);
    }
    //校验Category是否有重复
    @Override
    @Transactional
    public ProductCategory save(ProductCategory productCategory) {
        List<ProductCategory> shopType = categoryRepository.findByShopType(productCategory.getShopType());
        for (ProductCategory category : shopType){
            if (category.getCategoryType().equals(productCategory.getCategoryType())){
                throw new ShipException(ProductStatusEnum.CATEGORY_ERROR.getCode(),
                        ProductStatusEnum.CATEGORY_ERROR.getMessage());
            }
        }
        return categoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findByCategoryType(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    @Transactional
    public ProductCategory update(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }
}
