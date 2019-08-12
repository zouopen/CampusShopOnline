package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    //根据OrderId查找OrderDetail
    List<OrderDetail> findByOrderId(String orderId);

    //根据商店Type查找订单归属

    List<OrderDetail> findByShopType(Integer shopType);


    void deleteByOrderId(String orderId);

}
