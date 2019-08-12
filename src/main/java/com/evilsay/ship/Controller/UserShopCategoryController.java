package com.evilsay.ship.Controller;

import com.evilsay.ship.Annotation.NoRepeatSubmit;
import com.evilsay.ship.DataObject.ShopCategory;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Enums.ResultShopEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.ShopCategoryService;
import com.evilsay.ship.Utils.KeyUtil;
import com.evilsay.ship.Utils.PageBeanUtils;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/13 14:56
 * 4
 */

@Controller
@RequestMapping("/shopCategory")
public class UserShopCategoryController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @GetMapping("/list")
    public ModelAndView list(ModelAndView modelAndView){


        modelAndView.setViewName("/system/admin/shopCategory/list");

        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/page")
    public PageBeanUtils<ShopCategory> page(@RequestParam(value = "start", defaultValue = "1") int start,
                                            @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                            @RequestParam(value = "date", required = false) String date,
                                            @RequestParam(value = "search", required = false) String search,
                                            HttpServletRequest requestp){

        PageInfo<ShopCategory> all = shopCategoryService.findAll(start, pageSize);

        return new PageBeanUtils<>(all);
    }

    @NoRepeatSubmit
    @GetMapping("/add")
    public ModelAndView shopCategoryAdd(Map<String,Object> map){

        return new ModelAndView("system/admin/shopCategory/add",map);
    }


    @NoRepeatSubmit
    @ResponseBody
    @PostMapping("/save")
    public ResultVO save(@RequestParam("shopCategoryName") String shopCategoryName) {

        ShopCategory shopCategory = new ShopCategory(shopCategoryName, KeyUtil.genShopTypeKey());

        if (shopCategoryService.findName(shopCategoryName) != null) {

            throw new ShipException(ResultShopEnum.CATEGORY_ERROR);
        }

        shopCategoryService.save(shopCategory);

        return ResultVOUtil.success();

    }
    @GetMapping("/update")
    public ModelAndView update(@RequestParam("shopCategoryId") Integer shopCategoryId,Map<String,Object>map){

        ShopCategory shopCategory = shopCategoryService.finOne(shopCategoryId);

        map.put("shopCategory",shopCategory);

        return new ModelAndView("system/admin/shopCategory/update",map);
    }

    @PostMapping("/saveUpdate")
    @ResponseBody
    @NoRepeatSubmit
    public ResultVO saveupdate(@RequestParam("shopCategoryId") Integer shopCategoryId,@RequestParam("shopCategoryName") String shopCategoryName){
        ShopCategory shopCategory = shopCategoryService.finOne(shopCategoryId);

        if (shopCategoryService.findName(shopCategoryName) != null){

            throw new ShipException(ResultShopEnum.CATEGORY_ERROR);
        }

        shopCategory.setShopCategoryName(shopCategoryName);

        shopCategoryService.save(shopCategory);

        return ResultVOUtil.success();
    }


}
