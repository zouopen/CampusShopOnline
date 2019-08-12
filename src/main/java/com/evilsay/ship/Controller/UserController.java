package com.evilsay.ship.Controller;


import com.evilsay.ship.Config.ProjectUrlConfig;
import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Security.ShiroKit;
import com.evilsay.ship.Service.ShopInfoService;
import com.evilsay.ship.Utils.SellerUtils.ShopInfoUtils;
import com.evilsay.ship.Utils.VCUtils.CaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Map;


/**卖家登录
 * @Author: EvilSay
 * @Date: 2019/2/7 21:16
 */
@Controller
@Slf4j
public class UserController {



    @Autowired
    private ShopInfoService service;

    @Autowired
    private ShopInfoUtils  utils;

    @Autowired
    private ProjectUrlConfig urlConfig;

    @GetMapping("/index")
    public ModelAndView AdminIndex(Map<String,Object> map){
        ShiroUserDTO shiroUserDTO = (ShiroUserDTO) ShiroKit.getSubject().getPrincipal();
        map.put("shiroUserDTO",shiroUserDTO);
        return new ModelAndView("system/admin/index",map);
    }

    @GetMapping("/close")
    public ModelAndView close(Map<String,Object> map){
        Integer shopType = utils.getShopType();
        try {
            service.closeShop(shopType);
        }catch (ShipException e){
            log.error("[卖家关闭商店]商店状态异常");
            map.put("msg",e.getMessage());
            map.put("url","/seller/product/list");
            map.put("shoptype",shopType);
            return new ModelAndView("common/error",map);
        }
        map.put("msg","打烊了，明天又是美好的一天");
        map.put("url","/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/open")
    public ModelAndView open(Map<String,Object>map){
        Integer shopType = utils.getShopType();
        try {
            service.OpenShop(shopType);
        }catch (ShipException e){
            log.error("[卖家开启商店]商店状态异常");
            map.put("msg",e.getMessage());
            map.put("url","/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/seller/product/list");
        map.put("msg","半年不开张，开张吃半年");
        return new ModelAndView("common/success",map);
    }
}
