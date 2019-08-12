package com.evilsay.ship.Controller;

import com.evilsay.ship.DTO.OrderDTO;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.OrderDTOService;
import com.evilsay.ship.Utils.SellerUtils.ShopInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**卖家端订单
 * @Author: EvilSay
 * @Date: 2019/2/1 22:40
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class


SellerOrderController {

    @Autowired
    private OrderDTOService orderDTOService;

    @Autowired
    private ShopInfoUtils shopInfoUtils;
    //卖家店铺所有订单
    @GetMapping("/shoplist")
    public ModelAndView list(@RequestParam(value = "page",defaultValue ="1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> shopList = orderDTOService.findShopList(shopInfoUtils.getShopType(), request);

        map.put("shopList",shopList);
        map.put("curettage",page);
        map.put("size",size);
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        return new ModelAndView("order/shoplist",map);
    }
    //取消订单
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO oneDetail = orderDTOService.findOneDetail(orderId);
            orderDTOService.cancel(oneDetail);
        }catch (ShipException e){
            log.error("[卖家端取消订单]查询不到订单");
            map.put("msg",e.getMessage());
            map.put("url","/seller/order/shoplist");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/seller/order/shoplist");
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        return new ModelAndView("common/success",map);
    }
    //指定店铺的订单详情
    @GetMapping("/shopdetail")
    public ModelAndView shopDetail(@RequestParam("orderId") String orderId,
                                   Map<String,Object> map){
        OrderDTO oneDetail;
        try {
            oneDetail = orderDTOService.findOneDetail(orderId);
        }catch (ShipException e){
            log.error("[卖家端取消订单]查询不到订单");
            map.put("msg",e.getMessage());
            map.put("url","/seller/order/shoplist?shoptype");
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",oneDetail);
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        return new ModelAndView("order/detail",map);
    }
    //完结订单
    @GetMapping("finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderDTOService.findOneDetail(orderId);
            orderDTOService.finish(orderDTO);
        }catch (ShipException e){
            log.error("[卖家端取消订单]查询不到订单");
            map.put("msg",e.getMessage());
            map.put("url","/seller/order/shoplist?shoptype" );
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        map.put("url","/seller/order/shoplist?shoptype");
        return new ModelAndView("common/success",map);
    }
    //删除订单
    //慎重调用 ！！！！！！！
    @GetMapping("delete")
    public ModelAndView delete(@RequestParam("orderId")String orderId,
                               Map<String,Object> map){
        try {
            OrderDTO orderDTO = orderDTOService.findOneDetail(orderId);
            orderDTOService.delete(orderDTO);
        }catch (ShipException e){
            log.error("[卖家端取消订单]查询不到订单");
            map.put("msg",e.getMessage());
            map.put("url","/seller/order/shoplis");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS.getMessage());
        map.put("url","/seller/order/shoplist");
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        return new ModelAndView("common/success",map);
    }
}
