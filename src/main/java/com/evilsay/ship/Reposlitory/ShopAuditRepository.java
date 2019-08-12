package com.evilsay.ship.Reposlitory;

import com.evilsay.ship.DataObject.ShopAudit;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/21 13:16
 * 4
 */

public interface ShopAuditRepository extends JpaRepository<ShopAudit,Integer> {

    ShopAudit findByUsername(String username);

    List<ShopAudit> findAll ();
}
