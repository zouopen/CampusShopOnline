package com.evilsay.ship.Service;

import com.evilsay.ship.DTO.OrderDTO;
import com.lly835.bestpay.model.PayResponse;


/**微信支付服务接口
 * @Author: EvilSay
 * @Date: 2019/1/29 18:34
 */
public interface PayService {
    PayResponse create(OrderDTO orderDTO) ;

    PayResponse notify(String notifyData);
}
