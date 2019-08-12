package com.evilsay.ship.Controller.Buyer;

import com.evilsay.ship.Annotation.NoRepeatSubmit;
import com.evilsay.ship.DataObject.BuyerAddress;
import com.evilsay.ship.Enums.ResultAddressEnum;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.From.BuyerAddressFrom;
import com.evilsay.ship.Service.BuyerAddressService;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/26 16:40
 * 4
 */

@RestController
@RequestMapping("/buyer/address")
@Slf4j
public class BuyerAddressController {

    @Autowired
    private BuyerAddressService buyerAddressService;

    @PostMapping("/save")
    @NoRepeatSubmit
    public ResultVO save(@Valid BuyerAddressFrom buyerAddressFrom,
                         BindingResult bindingResult){
        //判断表单检验是否有错误
        if (bindingResult.hasErrors()){
            log.error("[创建用户地址]参数不正确,orderFrom={}",buyerAddressFrom);
            throw new ShipException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        List<BuyerAddress> buyerOpenid = buyerAddressService.findByBuyerOpenid(buyerAddressFrom.getBuyerOpenid());
        if (buyerOpenid.size() > 5){
            log.error("[创建用户地址]地址数量过多");
            throw new ShipException(ResultAddressEnum.ADDRESS_NUM_ERROR.getCode(),ResultAddressEnum.ADDRESS_NUM_ERROR.getMessage());
        }
        BuyerAddress buyerAddress = new BuyerAddress();
        BeanUtils.copyProperties(buyerAddressFrom,buyerAddress);
        buyerAddressService.save(buyerAddress);
        return ResultVOUtil.success();
    }

    @GetMapping("/list")
    @NoRepeatSubmit
    public ResultVO ByOpenidlist(@RequestParam("openid") String openid){

        if (openid == null||openid.trim().equals("")){
            log.error("[查询用户地址列表]openid为空");
            throw new ShipException(ResultAddressEnum.ADDRESS_OPENID_NULL.getCode(),ResultAddressEnum.ADDRESS_OPENID_NULL.getMessage());
        }

        List<BuyerAddress> buyerOpenid = buyerAddressService.findByBuyerOpenid(openid);
        return ResultVOUtil.success(buyerOpenid);
    }

}
