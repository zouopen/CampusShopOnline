package com.evilsay.ship.Controller.Buyer;

import com.evilsay.ship.Config.ProjectUrlConfig;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Exception.ShipException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**微信网页授权
 * @Author: EvilSay
 * @Date: 2019/1/28 21:18
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
@Api(tags ="微信网页授权API" )
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    @ApiOperation(value = "微信授权")
    public String authorize(@RequestParam("returnUrl")String returnUrl) throws UnsupportedEncodingException {
        // 配置
        String URL = projectUrlConfig.getWechatMpAuthorize() + "/wechat/userInfo";
        String authorizationUrl = wxMpService.oauth2buildAuthorizationUrl(URL, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl,"UTF-8"));
        log.info("[微信网页授权]获取code,authorizationUrl={}",authorizationUrl);

        return "redirect:" + authorizationUrl;
    }
    @GetMapping("/userInfo")
    @ApiOperation(value = "微信授权获取微信用户基本信息")
    public String userInfo(@RequestParam("code")String code,
                           @RequestParam("state")String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
            log.info("[微信网页授权] wxMpUser={}",wxMpUser.getNickname());
        } catch (WxErrorException e) {
            log.error("[微信网页授权] {}",e);
            throw new ShipException(ResultEnum.WX_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openId;
    }
    //微信第三方平台登录
    @GetMapping("/qrAuthorize")
    @ApiOperation(value = "微信第三方授权登录")
    public String qrAuthorize(@RequestParam("returnUrl")String returnUrl) throws UnsupportedEncodingException {
        String url = projectUrlConfig.getWechatOpneAuthorize()+"/maozhi/wechat/qrAuthorize";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url,WxConsts.QrConnectScope.SNSAPI_LOGIN,URLEncoder.encode(returnUrl,"UTF-8"));
        return "redirect:" + redirectUrl;
    }
    //微信第三方平台登录
    @GetMapping("qrUserInfo")
    @ApiOperation(value = "微信第三方授权登录")
    public String qrUserInfo(@RequestParam("code")String code,
                             @RequestParam("state")String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信网页授权] {}" ,e);
            throw new ShipException(ResultEnum.WX_LOGIN_ERROR);
        }
        log.info("wxMpOAuth2AccessToken={}",wxMpOAuth2AccessToken);
        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
