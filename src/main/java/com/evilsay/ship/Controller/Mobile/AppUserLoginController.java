package com.evilsay.ship.Controller.Mobile;

import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Security.ShiroKit;
import com.evilsay.ship.Service.Impl.ShiroImpl;
import com.evilsay.ship.Service.SellerInfoService;
import com.evilsay.ship.Service.ShopInfoService;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import com.evilsay.ship.VO.ShopVO.ShopUserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/3/29 20:27
 * 4
 */
 

@RestController
@Slf4j
public class AppUserLoginController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private ShiroImpl shiro;


    @PostMapping("/MobileLogin")
    public ResultVO userLogin(@RequestParam() String username,
                              @RequestParam() String password,
                              HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(username,password.toCharArray());

        try {

            ShiroKit.getSubject().login(token);

        } catch (AuthenticationException e) {
            log.error(e.getMessage());
            return ResultVOUtil.error(LoginStatusEnums.LOGIN_INFO_ERROR.getCode(),LoginStatusEnums.LOGIN_INFO_ERROR.getMessage());
        }

        SellerInfo sellerInfo = sellerInfoService.findByUserName(token.getUsername());

        ShiroUserDTO shiroUserDTO = shiro.shiroUser(sellerInfo);

        ShopUserLoginVO shopUserLoginVO = new ShopUserLoginVO();
        //判断是否为admin
        if (sellerInfo.getShopType() == null){
            BeanUtils.copyProperties(sellerInfo,shopUserLoginVO);
        }else{
            ShopInfo shopInfo = shopInfoService.findByShopType(sellerInfo.getShopType());
            BeanUtils.copyProperties(shopInfo,shopUserLoginVO);
        }
        shopUserLoginVO.setRole(shiroUserDTO.getRole());

        session.setAttribute("user",shiroUserDTO);

        return ResultVOUtil.success(shopUserLoginVO);
    }
}
