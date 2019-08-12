package com.evilsay.ship.Handler;

import com.evilsay.ship.Config.ProjectUrlConfig;
import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Exception.*;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: EvilSay
 * @Date: 2019/3/3 17:54
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //捕获切面异常登录
    @ExceptionHandler(value = LoginException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ModelAndView handlerLoginException(){
        Map<String,Object> map = new HashMap<>();
        map.put("msg", LoginStatusEnums.LOGIN_ERROR.getMessage());
        map.put("url","/login");
        return new ModelAndView("common/error",map);
    }

    //捕获PC端登录异常
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ModelAndView handlerAuthenticationException(){
        return new ModelAndView("redirect:"
                .concat("/login")
        );
    }

    @ExceptionHandler(value = MobileBlackUserException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultVO handlerMobileBlackUserException(){
        return ResultVOUtil.error(LoginStatusEnums.LOGIN_BLACK_ERROR.getCode(),LoginStatusEnums.LOGIN_BLACK_ERROR.getMessage());
    }


    //捕获黑名单异常
    @ExceptionHandler(value = PCBlackUserException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handlerPCBlackUserException(){
        return new ModelAndView("redirect:"
                .concat("/login")
        );
    }
    //捕获ShipException
    @ExceptionHandler(value = ShipException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultVO handlerSellerException(ShipException s){
        return ResultVOUtil.error(s.getCode(),s.getMessage());
    }

    //捕获PictureException
    @ExceptionHandler(value = PictureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO handlerPictureException(PictureException p){
        return ResultVOUtil.error(p.getMessage());
    }


}