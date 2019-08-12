package com.evilsay.ship.Service;

import com.evilsay.ship.DataObject.SellerSystemSecurity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/3/7 9:01
 */

public interface SellerSystemSecurityService {

    SellerSystemSecurity findOne(String id);

    SellerSystemSecurity save(SellerSystemSecurity sellerSystemSecurity);

    SellerSystemSecurity findIp(String ip);

    List<SellerSystemSecurity> findAll ();
}
