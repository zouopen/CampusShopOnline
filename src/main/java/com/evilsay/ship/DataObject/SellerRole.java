package com.evilsay.ship.DataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: EvilSay
 * @Date: 2019/2/12 17:44
 */
@Data
@Entity
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class SellerRole {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String value;

    private Integer status;
}
