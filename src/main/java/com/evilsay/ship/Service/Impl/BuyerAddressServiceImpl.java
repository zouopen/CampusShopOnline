package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.BuyerAddress;
import com.evilsay.ship.Reposlitory.BuyerAddressRepository;
import com.evilsay.ship.Service.BuyerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/26 15:52
 * 4
 */
@Service
public class BuyerAddressServiceImpl implements BuyerAddressService {

    @Autowired
    private BuyerAddressRepository repository;

    @Override
    public BuyerAddress save(BuyerAddress buyerAddress) {
        return repository.save(buyerAddress);
    }

    @Override
    public List<BuyerAddress> findByBuyerOpenid(String openId) {
        return repository.findByBuyerOpenid(openId);
    }
}
