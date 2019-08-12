package com.evilsay.ship.Controller.Buyer;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.OrderDTOService;
import com.evilsay.ship.Service.PayService;
import com.lly835.bestpay.model.PayResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**支付
 * @Author: EvilSay
 * @Date: 2019/1/31 16:23
 */
@Controller
@RequestMapping("/pay")
@Slf4j
@Api(tags = "微信支付接口API")
public class PayController {
    @Autowired
    private OrderDTOService orderDTOService;
    @Autowired
    private PayService payService;
    @GetMapping("/create")
    @ApiOperation(value = "支付")
    public ModelAndView create(@RequestParam("orderId")String orderId,
                               @RequestParam("returnUrl")String returnUrl,
                               Map<String,Object> map){
        OrderDTO orderDTO = orderDTOService.findOneDetail(orderId);
        if (orderDTO == null){
            throw new ShipException(ResultEnum.ORDER_NOT_EXIST);
        }
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }
    @ApiOperation(value = "微信支付异步回调")
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        //返回微信处理结果
        return new ModelAndView("pay/success");
    }
}
