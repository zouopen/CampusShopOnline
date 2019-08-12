package com.evilsay.ship.Controller;

import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Service.SafeService;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: EvilSay
 * @Date: 2019/4/17 16:47
 */
@RestController
@RequestMapping("seller/mobile/order")
@Slf4j
public class SellerMobileProductController  {
    @Autowired
    private SafeService safeService;

    @PostMapping("/list")
    public ResultVO list(@RequestParam("shopType")Integer shopType){

        return checkIsNull(shopType,true,safeService.findMobileOrderList(shopType));
    }
    @PostMapping("/detail")
    public ResultVO detail(@RequestParam("shopType")Integer shopType,
                           @RequestParam("orderId")String orderId){
        return checkIsNull(shopType,orderId,safeService.findMobileOrderOne(shopType,orderId));
    }
    private ResultVO checkIsNull(Object  Openid, Object types,Object ob) {
        if (StringUtils.isEmpty(Openid) || StringUtils.isEmpty(types)) {
            log.error("[查询订单]参数不能为空 URL参数一={},URL参数二 ={}", Openid, types);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        return ResultVOUtil.success(ob);
    }
}
