package com.evilsay.ship.Controller;

import com.evilsay.ship.DTO.SellerInfoDTO;
import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.DataObject.SellerUserRole;
import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.From.UserFrom;
import com.evilsay.ship.Security.ShiroKit;
import com.evilsay.ship.Service.SelleInfoDTOService;
import com.evilsay.ship.Service.SellerInfoService;
import com.evilsay.ship.Service.SellerUserRoleService;
import com.evilsay.ship.Utils.PageBeanUtils;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.Utils.SellerUtils.ShopInfoUtils;
import com.evilsay.ship.VO.ResultVO;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @Author: EvilSay
 * @Date: 2019/2/17 19:57
 */
@Controller
@Slf4j
@RequestMapping("/user")
public class UserMgrController {

    @Autowired
    private SelleInfoDTOService selleInfoDTOService;
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private SellerUserRoleService sellerUserRoleService;

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView){
        ShiroUserDTO shiroUserDTO = (ShiroUserDTO) ShiroKit.getSubject().getPrincipal();
        modelAndView.addObject("bean",shiroUserDTO);
        modelAndView.setViewName("/system/admin/user/add");
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam("id") Integer id,
                             ModelAndView modelAndView){
        SellerInfo sellerInfo = sellerInfoService.findById(id);

        modelAndView.addObject("bean", sellerInfo);

        modelAndView.setViewName("/system/admin/user/edit");
        return modelAndView;
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ResultVO<Object> update(@RequestParam("id") Integer id, String username) {
        if(sellerInfoService.findByUserName(username) != null){

            throw new ShipException(LoginStatusEnums.LOGIN_NAME_ERROR.getCode(),LoginStatusEnums.LOGIN_NAME_ERROR.getMessage());
        }
        SellerInfo sellerInfo = sellerInfoService.findById(id);

        sellerInfo.setUsername(username);

        sellerInfoService.save(sellerInfo);

        return ResultVOUtil.success();
    }

    @GetMapping(value = "/goResetPwd")
    public ModelAndView restPwd(@RequestParam("id") Integer id, ModelAndView modelAndView){
        modelAndView.setViewName("system/admin/user/reset_pwd");

        SellerInfo sellerInfo = sellerInfoService.findById(id);

        modelAndView.addObject("bean", sellerInfo);

        return modelAndView;
    }

    @GetMapping("/view")
    public ModelAndView view(@RequestParam("id") Integer id, ModelAndView modelAndView) {

        SellerInfoDTO sellerInfoDTO = selleInfoDTOService.findId(id);

        modelAndView.addObject("bean", sellerInfoDTO);

        modelAndView.setViewName("/system/admin/user/view");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView modelAndView){

            modelAndView.setViewName("system/admin/user/list");

        return modelAndView;
    }

    @ResponseBody
    @PostMapping("/page")
    public PageBeanUtils<SellerInfoDTO> page(@RequestParam(value = "start", defaultValue = "1") int start,
                                             @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "date", required = false) String date,
                                             @RequestParam(value = "search", required = false) String search,
                                             HttpServletRequest request){
        PageInfo<SellerInfoDTO> sellerInfoDTOPageInfo = selleInfoDTOService.findList(start,pageSize);

        return new PageBeanUtils<>(sellerInfoDTOPageInfo);
    }

    @ResponseBody
    @PostMapping("modifySave")
    public ResultVO modifySave(@RequestParam("id")Integer id,String password){
        SellerInfo sellerInfo = sellerInfoService.findById(id);
        SellerInfo info = ShopInfoUtils.resetPwd(sellerInfo, password);
        sellerInfoService.save(info);
        return ResultVOUtil.success();
    }

    @ResponseBody
    @GetMapping("/delete")
    public ResultVO delete(@RequestParam Integer id) {

        SellerInfo sellerInfo = sellerInfoService.findById(id);

        sellerUserRoleService.deleteByUserId(sellerInfo.getId());

        sellerInfoService.deleteByName(sellerInfo.getUsername());

        return ResultVOUtil.success();
    }

    @ResponseBody
    @PostMapping("save")
    public ResultVO save(@Valid UserFrom userFrom){

        SellerInfo sellerInfo = new SellerInfo();

        SellerUserRole sellerUserRole = new SellerUserRole();
        //属性copy
        BeanUtils.copyProperties(userFrom,sellerInfo);
        //设置密码加密属性
        SellerInfo sellerInfo1 = ShopInfoUtils.resetPwd(sellerInfo,userFrom.getPassword());

        if(sellerInfoService.findByUserName(userFrom.getUsername()) != null){

            throw new ShipException(LoginStatusEnums.LOGIN_NAME_ERROR.getCode(),LoginStatusEnums.LOGIN_NAME_ERROR.getMessage());
        }
        //保存到sellerInfo
        sellerInfoService.save(sellerInfo1);
        //以上同理
        BeanUtils.copyProperties(userFrom,sellerUserRole);

        sellerUserRole.setUserId(sellerInfo.getId());

        sellerUserRole.setRoleId(1);

        sellerUserRoleService.save(sellerUserRole);

        return ResultVOUtil.success();
    }

}
