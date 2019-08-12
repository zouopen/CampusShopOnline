package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.Converter.SellerInfo2SellerInfoDTOConverter;
import com.evilsay.ship.DTO.SellerInfoDTO;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.DataObject.SellerUserRole;
import com.evilsay.ship.Reposlitory.SellerInfoRepository;
import com.evilsay.ship.Reposlitory.SellerUserRoleReposlitory;
import com.evilsay.ship.Service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/2/18 22:31
 */
@Service
public class SelleInfoDTOImpl implements SelleInfoDTOService {
    @Autowired
    private SellerInfo2SellerInfoDTOConverter sellerInfo2SellerInfoDTOConverter;
    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public PageInfo<SellerInfoDTO> findList(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }

        List<SellerInfo> all = sellerInfoRepository.findAll();

        List<SellerInfoDTO> sellerInfoList = sellerInfo2SellerInfoDTOConverter.converter(all);

        return new PageInfo<>(sellerInfoList);
    }

    @Override
    public SellerInfoDTO findId(Integer id) {
        if (id != null){
            SellerInfo one = sellerInfoRepository.findOne(id);
            return sellerInfo2SellerInfoDTOConverter.Converter(one);
        }
        return null;
    }
}
