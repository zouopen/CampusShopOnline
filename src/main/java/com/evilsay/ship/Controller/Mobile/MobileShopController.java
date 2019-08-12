package com.evilsay.ship.Controller.Mobile;

import com.evilsay.ship.Annotation.NoRepeatSubmit;
import com.evilsay.ship.Converter.SellerInfo2SellerInfoDTOConverter;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.DataObject.SellerUserRole;
import com.evilsay.ship.DataObject.ShopAudit;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Enums.ShopStatusEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.From.MobileShopFrom;
import com.evilsay.ship.Service.*;
import com.evilsay.ship.Service.Impl.MobileWebSocketImpl;
import com.evilsay.ship.Utils.CheckPhone;
import com.evilsay.ship.Utils.KeyUtil;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/4 20:15
 * 4
 */
@RestController
@RequestMapping("/mobile")
@Slf4j
public class MobileShopController {


    @Autowired
    private ShopAuditService shopAuditService;

    @Autowired
    private SellerInfoService sellerInfoService;


    @Autowired
    private PictureBedService bedService;
    @Autowired
    private MobileWebSocketImpl webSocket;


    @NoRepeatSubmit
    @PostMapping("/save")
    public ResultVO shopSave(@Valid MobileShopFrom mobileShopFrom,
                             @RequestParam(value = "file",required = false)MultipartFile file,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            log.error("【申请店铺】参数不正确");

            throw new ShipException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());

        }
        ShopAudit byUserName = shopAuditService.findByUsername(mobileShopFrom.getUsername());

        if (byUserName != null) {

            log.error("【申请店铺】帐号已申请过,正在等待审核");

            throw new ShipException(ResultEnum.TO_EXAMINE_ERROR);
        }

        if (!CheckPhone.isCorrectPhone(mobileShopFrom.getShopPhone())){

            throw new ShipException(ResultEnum.PHONE_ERROR);
        }

        ShopAudit shopAudit = new ShopAudit();
        try {

            String img = bedService.uploadImageToQiniuyun(file);

            mobileShopFrom.setShopImg(img);

            BeanUtils.copyProperties(mobileShopFrom,shopAudit);
            //添加到审核表
            shopAuditService.save(shopAudit);

            webSocket.sendMessage("有新店铺申请审核");

            return ResultVOUtil.success(ResultEnum.APPLY_SHOP_SUCCESS.getMessage());

        } catch (ShipException | IOException e) {

            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());

        }
    }

    @PostMapping("check")
    public ResultVO check(@RequestParam("account")String account){
        SellerInfo sellerInfo = sellerInfoService.findByUserName(account);

        if (sellerInfo != null){

            throw new ShipException(LoginStatusEnums.LOGIN_NAME_ERROR.getCode(),LoginStatusEnums.LOGIN_NAME_ERROR.getMessage());

        }
        return ResultVOUtil.success();
    }
}
