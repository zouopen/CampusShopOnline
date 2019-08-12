package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DataObject.SellerSystemSecurity;
import com.evilsay.ship.Reposlitory.SellerSystemSecurityRepository;
import com.evilsay.ship.Service.SellerSystemSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/3/7 9:02
 */
@Service
public class SellerSystemSecurityServiceImpl implements SellerSystemSecurityService {

    @Autowired
    private SellerSystemSecurityRepository repository;

    @Override
    public SellerSystemSecurity findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public SellerSystemSecurity save(SellerSystemSecurity sellerSystemSecurity) {
//        保存之前判断数据库是否存在该IP
        SellerSystemSecurity systemSecurity = repository.findBySellerIp(sellerSystemSecurity.getSellerIp());
        if (systemSecurity == null){
            return repository.save(sellerSystemSecurity);
        }else{
            systemSecurity.setSellerRepeat(systemSecurity.getSellerRepeat() + 1);
            return repository.save(systemSecurity);
        }
    }

    @Override
    public SellerSystemSecurity findIp(String ip) {
        return repository.findBySellerIp(ip);
    }

    @Override
    public List<SellerSystemSecurity> findAll() {
        return repository.findAll();
    }
}
