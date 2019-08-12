package com.evilsay.ship.Controller.Buyer;

import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.From.OrderFrom;
import com.evilsay.ship.Converter.OrderFrom2OrderDTOConverter;
import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.OrderDTOService;
import com.evilsay.ship.Service.SafeService;
import com.evilsay.ship.Utils.CheckPhone;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**(个人)订单以及订单详情API
 * @Author: EvilSay
 * @Date: 2019/1/25 18:57
 */
@RestController
@Slf4j
@RequestMapping("/buyer/order")
@Api(tags = "用户创建订单API")
public class BuyerOrderController {
    @Autowired
    private OrderDTOService orderDTOService;
    @Autowired
    private SafeService safeService;
    //创建订单
    @ApiOperation(value = "创建订单",notes = "返回一个orderId")
    @ApiImplicitParam(name = "items", value = "购物车")
    @PostMapping("/create")
    public ResultVO create(@Valid OrderFrom orderFrom, BindingResult bindingResult){
        //判断表单检验是否有错误
        if (bindingResult.hasErrors()){
            log.error("[创建订单]参数不正确,orderFrom={}",orderFrom);
            throw new ShipException(ResultEnum.PRODUCT_STOCK_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        if (!CheckPhone.isCorrectPhone(orderFrom.getPhone())){
            throw new ShipException(ResultEnum.PHONE_ERROR);
        }
        OrderDTO orderDTO = OrderFrom2OrderDTOConverter.convert(orderFrom);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单]购物车不能位空");
            throw new ShipException(ResultEnum.CART_EMPTY);
        }
        OrderDTO create = orderDTOService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",create.getOrderId());
        return ResultVOUtil.success(map);
    }
    //返回用户订单列表
    @ApiOperation(value = "返回用户订单列表")
    @GetMapping("/list")
    public ResultVO list(@RequestParam("openid")String openid,
                         @RequestParam(value = "page",defaultValue = "0")Integer page,
                         @RequestParam(value = "size",defaultValue = "10")Integer size){
        PageRequest request = new PageRequest(page, size);
        return checkIsNull(openid,true,orderDTOService.findList(openid, request).getContent());
    }

    //用户订单详情
    @ApiOperation(value = "返回用户订单列表详情")
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){

        return checkIsNull(openid,orderId,safeService.finOrderOne(openid, orderId));
    }
    //用户取消订单
    @ApiOperation(value = "用户取消订单")
    @GetMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        return checkIsNull(openid,orderId,safeService.cancelOrder(openid, orderId));
    }
    //参数异常判断
    private ResultVO checkIsNull(Object Openid, Object types,Object ob){
        if (StringUtils.isEmpty(Openid)||StringUtils.isEmpty(types)){
            log.error("[查询订单]参数不能为空 URL参数一={},URL参数二 ={}",Openid,types);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        return ResultVOUtil.success(ob);
    }
}
