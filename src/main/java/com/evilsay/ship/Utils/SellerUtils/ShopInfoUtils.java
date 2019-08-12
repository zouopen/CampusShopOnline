package com.evilsay.ship.Utils.SellerUtils;

import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Security.ShiroKit;
import com.evilsay.ship.Service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: EvilSay
 * @Date: 2019/2/13 23:56
 */
@Component
public class ShopInfoUtils {
    @Autowired
    private ShopInfoService shopInfoService;

    public Map<String,Object> shopInfoUtils(Map<String,Object>map){
        ShopInfo shopInfo = shopInfoService.findByShopType(getShopType());
        map.put("shopInfo",shopInfo);
        map.put("shoptype",getShopType());

        return map;
    }

    public Integer getShopType(){

        ShiroUserDTO shiroUserDTO = (ShiroUserDTO) ShiroKit.getSubject().getPrincipal();

        return shiroUserDTO.getShopType();
    }


    public static SellerInfo resetPwd (SellerInfo sellerInfo,String password){
        sellerInfo.setSalt(ShiroKit.getRandomSalt(5));
        sellerInfo.setPassword(ShiroKit.md5(password,sellerInfo.getSalt()));
        sellerInfo.setStatus(1);
        return sellerInfo;
    }
}
