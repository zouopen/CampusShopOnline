package com.evilsay.ship.Service;

import com.evilsay.ship.DTO.SellerInfoDTO;
import com.evilsay.ship.DataObject.SellerInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * @Author: EvilSay
 * @Date: 2019/2/18 22:24
 */
public interface SelleInfoDTOService {

    PageInfo<SellerInfoDTO> findList(Integer pageNum, Integer pageSize);

    SellerInfoDTO findId(Integer id);
}
