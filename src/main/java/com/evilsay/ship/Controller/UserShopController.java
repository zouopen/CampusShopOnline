package com.evilsay.ship.Controller;

import com.evilsay.ship.Converter.SellerInfo2SellerInfoDTOConverter;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.DataObject.SellerUserRole;
import com.evilsay.ship.DataObject.ShopCategory;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.From.ShopFrom;
import com.evilsay.ship.From.UserFrom;
import com.evilsay.ship.Service.*;
import com.evilsay.ship.Utils.CheckPhone;
import com.evilsay.ship.Utils.KeyUtil;
import com.evilsay.ship.Utils.PageBeanUtils;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.Utils.SellerUtils.ShopInfoUtils;
import com.evilsay.ship.VO.ResultVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.evilsay.ship.Enums.LoginStatusEnums.LOGIN_NAME_ERROR;
import static com.evilsay.ship.Enums.ResultEnum.PHONE_ERROR;

/** 店铺控制信息
 * @Author: EvilSay
 * @Date: 2019/2/19 0:45
 */
@Controller
@RequestMapping("/shop")
@Slf4j
public class UserShopController {

    @Autowired
    private ShopInfoService shopInfoService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private SellerUserRoleService sellerUserRoleService;

    @Autowired
    private PictureBedService bedService;

    @GetMapping("list")
    public ModelAndView list(ModelAndView modelAndView){

        modelAndView.setViewName("/system/admin/shop/list");

        return modelAndView;
    }

    @ResponseBody
    @PostMapping("page")
    public PageBeanUtils<ShopInfo> page(@RequestParam(value = "start", defaultValue = "1") int start,
                                        @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "date", required = false) String date,
                                        @RequestParam(value = "search", required = false) String search,
                                        HttpServletRequest request){

        PageInfo<ShopInfo> all = shopInfoService.findAll(start, pageSize);

        return new PageBeanUtils<>(all);
    }

    @GetMapping("add")
    public ModelAndView shopAdd(ModelAndView modelAndView){
        List<ShopCategory> categoryList = shopCategoryService.findAll();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("system/admin/shop/add");
        return modelAndView;
    }
    @GetMapping("edit")
    public ModelAndView shopEdit(@RequestParam("id") Integer shopId, Map<String,Object> map){

        ShopInfo shopInfo = shopInfoService.findOne(shopId);

        SellerInfo sellerInfo = sellerInfoService.findByShop(shopInfo.getShopType());

        List<ShopCategory> categoryList = shopCategoryService.findAll();

        map.put("shopInfo", shopInfo);

        map.put("sellerInfo", sellerInfo);

        map.put("categoryList", categoryList);

        return new ModelAndView("system/admin/shop/edit",map);
    }
    @ResponseBody
    @PostMapping("save")
    public ResultVO shopSave(@Valid ShopFrom shopFrom, BindingResult bindingResult,
                             @RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        if (bindingResult.hasErrors()) {

            throw new ShipException(ResultEnum.ERROR);
        }
        ShopInfo shopInfo = new ShopInfo();
        try {
            if (!CheckPhone.isCorrectPhone(shopFrom.getShopPhone())) {

                throw new ShipException(PHONE_ERROR);
            }
            if (sellerInfoService.findByUserName(shopFrom.getUsername()) != null) {

                throw new ShipException(LOGIN_NAME_ERROR.getCode(), LOGIN_NAME_ERROR.getMessage());
            }
            if (shopInfoService.findByShopName(shopFrom.getShopName()) != null) {

                throw new ShipException(ResultEnum.SHOP_NAME_REPLACE_ERROR);
            }
            String image = bedService.uploadImageToQiniuyun(multipartFile);

            shopFrom.setShopImg(image);

            BeanUtils.copyProperties(shopFrom, shopInfo);

            shopInfo.setShopType(KeyUtil.genShopTypeKey());

            shopInfoService.save(shopInfo);

            SellerInfo sellerInfo1 = SellerInfo2SellerInfoDTOConverter.converter(shopInfo, shopFrom, null);

            sellerInfoService.save(sellerInfo1);

            SellerUserRole sellerUserRole = SellerInfo2SellerInfoDTOConverter.converter(sellerInfo1);

            sellerUserRoleService.save(sellerUserRole);

        } catch (IOException e) {

            throw new ShipException(ResultEnum.ERROR.getCode(),"上传图片失败请重新上传");

        }
        return ResultVOUtil.success();
    }
    @ResponseBody
    @PostMapping("update")
    public ResultVO shopUpdate(@RequestParam(value = "file",required = false)MultipartFile multipartFile,ShopFrom shopFrom){
        try {
            if (!StringUtils.isEmpty(shopFrom.getShopId()) && !StringUtils.isEmpty(shopFrom.getUserId())){
                if (!CheckPhone.isCorrectPhone(shopFrom.getShopPhone())) {

                    throw new ShipException(PHONE_ERROR);
                }
                ShopInfo shopInfo = shopInfoService.findOne(shopFrom.getShopId());

                SellerInfo sellerInfo = sellerInfoService.findById(shopFrom.getUserId());

                String image = bedService.uploadImageToQiniuyun(multipartFile);

                shopFrom.setShopType(shopInfo.getShopType());

                shopFrom.setShopImg(image);

                shopFrom.setPassword(sellerInfo.getPassword());

                BeanUtils.copyProperties(shopFrom,sellerInfo);

                BeanUtils.copyProperties(shopFrom,shopInfo);

                shopInfoService.save(shopInfo);

                sellerInfoService.save(sellerInfo);

                return ResultVOUtil.success();
            }

        }catch (IOException e){

            throw new ShipException(ResultEnum.ERROR.getCode(),"上传图片失败请重新上传");
        }

        return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());

    }

    @ResponseBody
    @GetMapping("delete")
    public ResultVO shopDelete(@RequestParam("id")Integer shopId){

        ShopInfo shopInfo = shopInfoService.findOne(shopId);

        SellerInfo SellerInfo = sellerInfoService.findByShop(shopInfo.getShopType());

        sellerUserRoleService.deleteByUserId(SellerInfo.getId());

        sellerInfoService.deleteByShopType(shopInfo.getShopType());

        shopInfoService.deleteById(shopId);

        return ResultVOUtil.success();
    }
}
