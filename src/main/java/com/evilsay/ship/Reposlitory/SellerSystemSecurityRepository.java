package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.SellerSystemSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: EvilSay
 * @Date: 2019/3/7 8:54
 */
public interface SellerSystemSecurityRepository extends JpaRepository<SellerSystemSecurity,String> {

//    根据IP添加次数
    SellerSystemSecurity findBySellerIp (String ip);


}
