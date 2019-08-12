package com.evilsay.ship.Controller;

import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Service.ShopInfoService;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: EvilSay
 * @Date: 2019/4/22 17:05
 */
@RestController
@RequestMapping("/seller/mobile")
@Slf4j
public class SellerMobileController {
    @Autowired
    private ShopInfoService shopInfoService;

    @PostMapping("/open")
    public ResultVO openShop(@RequestParam("shopType")Integer shopType){
        shopInfoService.OpenShop(shopType);
        ShopInfo byShopType = shopInfoService.findByShopType(shopType);
        return ResultVOUtil.success(byShopType);
    }
    @PostMapping("/close")
    public ResultVO closeShop(@RequestParam("shopType")Integer shopType) {
        shopInfoService.closeShop(shopType);
        ShopInfo byShopType = shopInfoService.findByShopType(shopType);
        return ResultVOUtil.success(byShopType);
    }

    @PostMapping("/status")
    public ResultVO shopStatus(@RequestParam("shopType")Integer shopType) {
        ShopInfo byShopType = shopInfoService.findByShopType(shopType);
        Integer shopStatus = byShopType.getShopStatus();
        return ResultVOUtil.success_status(String.valueOf(shopStatus));
    }
}
