package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    //根据买家openid查询订单,以分页的形式展示
    Page<OrderMaster> findByBuyerOpenid (String openId,Pageable pageable);

    List<OrderMaster> findByShopType(Integer shopType, Sort sort);

    Page<OrderMaster> findByShopType(Integer shopType,Pageable pageable);

    OrderMaster findByBuyerOpenid (String openid);

    @Transactional
    void deleteByBuyerOpenid (String buyerId);

}
