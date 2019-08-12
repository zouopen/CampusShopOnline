package com.evilsay.ship.Service;

import com.evilsay.ship.DataObject.BuyerAddress;

import java.util.List;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/26 15:30
 * 4
 */
public interface BuyerAddressService {

    BuyerAddress save(BuyerAddress buyerAddress);

    List<BuyerAddress> findByBuyerOpenid(String openId);

}
