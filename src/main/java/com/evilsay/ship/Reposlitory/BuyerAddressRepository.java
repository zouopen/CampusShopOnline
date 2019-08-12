package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/26 14:54
 * 4
 */
public interface BuyerAddressRepository extends JpaRepository<BuyerAddress,Integer> {

    //通过微信Openid查地址
    List<BuyerAddress> findByBuyerOpenid(String openId);

}
