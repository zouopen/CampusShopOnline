package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.OrderDTOService;
import com.evilsay.ship.Service.PayService;
import com.evilsay.ship.Utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**微信支付发起
 * @Author: EvilSay
 * @Date: 2019/1/29 18:55
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "茂名职业技术电商平台";

    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderDTOService orderDTOService;
    @Override
    @Transactional
    public PayResponse create(OrderDTO orderDTO)  {
        PayRequest payRequest = new PayRequest();

        payRequest.setOpenid(orderDTO.getBuyerOpenid());

        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());

        payRequest.setOrderId(orderDTO.getOrderId());

        payRequest.setOrderName(ORDER_NAME);

        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);

//        log.info("[微信支付]发起支付request={}",JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);

//        log.info("[微信支付]发起支付payResponse={}",JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    @Transactional
    public PayResponse notify(String notifyData) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
//        log.info("[微信支付]异步通知,payResponse={}",JsonUtil.toJson(payResponse));

        //修改订单状态
        OrderDTO orderDTO = orderDTOService.findOneDetail(payResponse.getOrderId());
        if (orderDTO == null){
            log.error("[微信支付]订单不存在,orderId={}",payResponse.getOrderId());
            throw new ShipException(ResultEnum.ORDER_NOT_EXIST);
        }
//      校验本地端与服务端的金额
        if (!MathUtil.equals(payResponse.getOrderAmount(),orderDTO.getOrderAmount().doubleValue())){
            log.error("[微信支付]异步通知,订单金额不一致,orderId={},微信通知金额={},系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    payResponse.getOrderAmount());
            throw new ShipException(ResultEnum.PAY_NOTIFY_MONEY_VERIFY_E);
        }
        orderDTOService.payId(orderDTO);
        return payResponse;
    }
}
