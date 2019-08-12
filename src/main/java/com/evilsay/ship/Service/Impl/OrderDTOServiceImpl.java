package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.Converter.OrderMaster2OrderDTOConverter;
import com.evilsay.ship.DTO.CartDTO;
import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.DataObject.OrderDetail;
import com.evilsay.ship.DataObject.OrderMaster;
import com.evilsay.ship.DataObject.ProductInfo;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.*;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Reposlitory.OrderDetailRepository;
import com.evilsay.ship.Reposlitory.OrderMasterRepository;
import com.evilsay.ship.Reposlitory.ShopInfoRepository;
import com.evilsay.ship.Service.OrderDTOService;
import com.evilsay.ship.Service.ProductInfoService;
import com.evilsay.ship.Service.PushPayloadService;
import com.evilsay.ship.Utils.KeyUtil;
import com.evilsay.ship.Utils.SellerUtils.ShopInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 21:31
 */
@Service
@Slf4j
public class OrderDTOServiceImpl implements OrderDTOService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository repository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ShopInfoRepository shopInfoRepository;
    @Autowired
    private WebSocketImpl webSocket;
    @Autowired
    private OrderMaster2OrderDTOConverter orderMaster2OrderDTOConverter;
    @Autowired
    private PushPayloadService pushPayloadService;

    private CartDTO cartDTO;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        BigDecimal productPrice;
        List<CartDTO> cartDTOList = new ArrayList<>();
        // 查询商品(数量价格)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            ShopInfo shopInfo = shopInfoRepository.findByShopType(productInfo.getShopType());
            if (productInfo == null || shopInfo == null){
                throw new ShipException(ResultShopEnum.SHOP_INFO_NOT_EXITS,ResultEnum.PRODUCT_NOT_EXIST);
            }
            if (productInfo.getProductDiscount() == null){
                //计算商品的单价
                productPrice = new BigDecimal(BigInteger.ONE).multiply(productInfo.getProductPrice());
                orderAmount = productPrice.multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            }else{
                //计算商品的单价
                productPrice = productInfo.getProductDiscount().multiply(productInfo.getProductPrice());
                orderAmount = productPrice.multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            }
            // 计算订单总价
            // 订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            repository.save(orderDetail);
            // 扣库存
            cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity(),orderDetail.getShopType());
            cartDTOList.add(cartDTO);
        }
        // 写入数据库(orderMaster AND OrderDetail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setBuyerOpenid(orderDTO.getBuyerOpenid().trim());
        orderMaster.setOrderStatus(OrderMasterStatusEnum.NEW.getCode());
        orderMaster.setShopType(cartDTO.getShopType());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);
        //验证店铺值发送webSocket消息
        productInfoService.decreaseStock(cartDTOList);
        pushPayloadService.sendAppointApp("来自" + orderMaster.getBuyerAddress() + "的新订单",String.valueOf(orderMaster.getShopType()));
        webSocket.sendMessage("来自" + orderMaster.getBuyerAddress() + "的新订单",orderMaster.getShopType());
        return orderDTO;
    }
    //查询订单详情
    @Override
    public OrderDTO findOneDetail(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        List<OrderDetail> orderDetailList = repository.findByOrderId(orderId);
        if (orderMaster == null){
            throw new ShipException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new ShipException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);

        List<OrderDTO> orderDTOList = orderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());


    }

    @Override
    public Page<OrderDTO> findShopList(Integer shopType, Pageable pageable) {

        Page<OrderMaster> orderMasterShopPage = orderMasterRepository.findByShopType(shopType, pageable);

        List<OrderDTO> orderDTOShopList = orderMaster2OrderDTOConverter.convert(orderMasterShopPage.getContent());

        return new PageImpl<>(orderDTOShopList,pageable,orderMasterShopPage.getTotalElements());
    }
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //查询店铺
        List<OrderDTO> shopOrderList = findShopOrderList(orderDTO.getShopType());

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderMasterStatusEnum.NEW.getCode())){
            log.error("[取消订单] 订单状态不正确, orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new ShipException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderMasterStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster save = orderMasterRepository.save(orderMaster);
        if (save == null){
            log.info("[取消订单]更新订单,orderMaster={}",orderMaster);
            throw new ShipException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.info("[取消订单]订单详情为空 orderDto={}",orderDTO);
            throw new ShipException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity(),e.getShopType()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);
        //如果已支付退款
        if (orderDTO.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO 支付完退款
        }
        return orderDTO;
    }
    @Transactional
    @Override
    public OrderDTO payId(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderMasterStatusEnum.NEW.getCode())){
            log.error("[订单支付] 订单状态不正确, orderId={}, orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new ShipException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[订单支付] 订单支付状态不正确,orderDTO={}",orderDTO);
            throw new ShipException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster pay = orderMasterRepository.save(orderMaster);
        if (pay == null){
            log.error("[订单支付] 订单状态不正确, orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new ShipException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findShopOrderList(Integer shopType) {
        //查询店铺信息
        List<OrderMaster> orderMasterList = orderMasterRepository.findByShopType(shopType,new Sort(Sort.Direction.DESC,"createTime"));
        List<OrderDTO> orderDTOList = new ArrayList<>();
        //判断店铺信息状态
        if (CollectionUtils.isEmpty(orderMasterList)) {
            throw new ShipException(ResultShopEnum.SHOP_NOT_EXITS);
        }
        for (OrderMaster orderMaster : orderMasterList) {
            //存入店铺订单详情
            OrderDTO one = findOneDetail(orderMaster.getOrderId());
            if (one == null){
                throw new ShipException(ResultShopEnum.SHOP_NOT_EXITS);
            }
            BeanUtils.copyProperties(orderMaster,one);
            orderDTOList.add(one);
        }
        return orderDTOList;
    }
    @Transactional
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderMasterStatusEnum.NEW.getCode())){
            log.error("[完结订单] 订单状态不正确, orderId={}, orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new ShipException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderMasterStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster finish = orderMasterRepository.save(orderMaster);
        if (finish == null){
            log.error("[完结订单] 订单状态不正确, orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new ShipException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderDTO;
    }

    /**
     *
     * @param orderDTO 该操作为高危操作。慎重调用
     */
    @Transactional
    @Override
    public void delete(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderMasterStatusEnum.CANCEL.getCode())){
            log.error("[删除订单]订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new ShipException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMasterRepository.delete(orderDTO.getOrderId());
        repository.deleteByOrderId(orderDTO.getOrderId());
    }
    @Override
    public String getTotalSum(Integer shopType) {
//        List<OrderMaster> orderMasters = orderMasterRepository.findByShopType(shopType);
//        BigDecimal bigDecimal = new BigDecimal(BigInteger.ZERO);
//        for (OrderMaster orderMaster : orderMasters){
//            BigDecimal orderAmount = orderMaster.getOrderAmount();
//            bigDecimal = bigDecimal.add(orderAmount);
//        }
//        return bigDecimal.toString();
        return null;
    }

}
