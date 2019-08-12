package com.evilsay.ship.Converter;

import com.evilsay.ship.DTO.SellerInfoDTO;
import com.evilsay.ship.DataObject.*;
import com.evilsay.ship.From.ShopFrom;
import com.evilsay.ship.Service.SellerRoleService;
import com.evilsay.ship.Service.SellerUserRoleService;
import com.evilsay.ship.Service.ShopInfoService;
import com.evilsay.ship.Utils.SellerUtils.ShopInfoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: EvilSay
 * @Date: 2019/2/18 22:37
 */
@Component
public class SellerInfo2SellerInfoDTOConverter {

    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private SellerUserRoleService sellerUserRoleService;

    @Autowired
    private SellerRoleService sellerRoleService;

    public SellerInfoDTO Converter(SellerInfo sellerInfo){

        SellerInfoDTO sellerInfoDTO  = new SellerInfoDTO();

        BeanUtils.copyProperties(sellerInfo,sellerInfoDTO);

        ShopInfo shopType = shopInfoService.findByShopType(sellerInfo.getShopType());

        if (sellerInfo.getShopType() != null && shopType != null){

            sellerInfoDTO.setShopName(shopType.getShopName());

        }

        SellerUserRole sellerUserRole = sellerUserRoleService.findByUserId(sellerInfo.getId());

        SellerRole sellerRoleServiceOne = sellerRoleService.findOne(sellerUserRole.getRoleId());

        sellerInfoDTO.setCreateBy(sellerUserRole.getCreateBy());

        sellerInfoDTO.setRoleName(sellerRoleServiceOne.getName());

        return sellerInfoDTO;
    }

    public List<SellerInfoDTO> converter(List<SellerInfo> sellerInfoList){
        return sellerInfoList.stream().map(this::Converter).collect(Collectors.toList());
    }

    public static SellerInfo converter(ShopInfo shopInfo, ShopFrom shopFrom, ShopAudit shopAudit){

        SellerInfo sellerInfo = new SellerInfo();
        if (shopFrom != null){

            BeanUtils.copyProperties(shopFrom,sellerInfo);

            ShopInfoUtils.resetPwd(sellerInfo,shopFrom.getPassword());

        }else {

            BeanUtils.copyProperties(shopAudit,sellerInfo);

            ShopInfoUtils.resetPwd(sellerInfo,shopAudit.getPassword());

        }
        sellerInfo.setShopType(shopInfo.getShopType());

        return sellerInfo;

    }

    public static SellerUserRole converter(SellerInfo sellerInfo){

        SellerUserRole sellerUserRole = new SellerUserRole();

        sellerUserRole.setUserId(sellerInfo.getId());

        sellerUserRole.setRoleId(3);

        return sellerUserRole;
    }
}
