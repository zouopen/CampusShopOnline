package com.evilsay.ship.Controller;

import com.evilsay.ship.Converter.SellerInfo2SellerInfoDTOConverter;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.DataObject.SellerUserRole;
import com.evilsay.ship.DataObject.ShopAudit;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.ShopStatusEnum;
import com.evilsay.ship.Service.SellerInfoService;
import com.evilsay.ship.Service.SellerUserRoleService;
import com.evilsay.ship.Service.ShopAuditService;
import com.evilsay.ship.Service.ShopInfoService;
import com.evilsay.ship.Utils.KeyUtil;
import com.evilsay.ship.Utils.PageBeanUtils;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: EvilSay
 * @Date: 2019/4/27 22:28
 */
@Controller
@RequestMapping("/examine")
public class UserMobileExamineController {

    @Autowired
    private ShopAuditService shopAuditService;

    @Autowired
    private ShopInfoService  shopInfoService;

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private SellerUserRoleService sellerUserRoleService;

    @GetMapping("list")
    public ModelAndView list(ModelAndView modelAndView){

        modelAndView.setViewName("/system/admin/mobile/list");

        return modelAndView;
    }

    @ResponseBody
    @PostMapping("page")
    public PageBeanUtils<ShopAudit> page(@RequestParam(value = "start", defaultValue = "1") int start,
                                        @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "date", required = false) String date,
                                        @RequestParam(value = "search", required = false) String search,
                                        HttpServletRequest request){

        PageInfo<ShopAudit> all = shopAuditService.findAll(start, pageSize);

        return new PageBeanUtils<>(all);
    }
    @ResponseBody
    @GetMapping("/success")
    public ResultVO success(@RequestParam("id") Integer id){

        ShopAudit shopAudit = shopAuditService.findOne(id);

        ShopInfo shopInfo = new ShopInfo();

        BeanUtils.copyProperties(shopAudit,shopInfo);

        shopInfo.setShopType(KeyUtil.genShopTypeKey());

        shopInfo.setShopStatus(ShopStatusEnum.OFFLINE.getCode());

        shopInfoService.save(shopInfo);

        SellerInfo sellerInfo = SellerInfo2SellerInfoDTOConverter.converter(shopInfo,null, shopAudit);

        sellerInfoService.save(sellerInfo);

        SellerUserRole sellerUserRole =  SellerInfo2SellerInfoDTOConverter.converter(sellerInfo);

        sellerUserRoleService.save(sellerUserRole);

        shopAuditService.DeleteById(id);

        return ResultVOUtil.success();
    }
    @ResponseBody
    @GetMapping("/UnSuccess")
    public ResultVO UnSuccess(@RequestParam("id") Integer id){

        shopAuditService.DeleteById(id);

        return ResultVOUtil.success();
    }
}
