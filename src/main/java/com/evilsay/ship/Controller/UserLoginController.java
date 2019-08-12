package com.evilsay.ship.Controller;

import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Security.ShiroKit;
import com.evilsay.ship.Utils.VCUtils.CaptchaUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @Author: EvilSay
 * @Date: 2019/3/5 12:32
 */
@Controller
public class UserLoginController {


    private static final String KEY_CAPTCHA = "KEY_CAPTCHA";

    @GetMapping("/login")
    public ModelAndView getLogin(ModelAndView modelAndView){
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @PostMapping("/postLogin")
    public String postLogin(@RequestParam() String username,
                            @RequestParam() String password,
//                            @RequestParam() String captcha,
                            HttpSession session, ModelAndView modelAndView) throws AuthenticationException {

        //验证密码
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        //获取验证码
//        String sessionCaptcha = (String) ShiroKit.getSubject().getSession().getAttribute(KEY_CAPTCHA);

        ShiroUserDTO shiroUserDTO = (ShiroUserDTO) ShiroKit.getSubject().getPrincipal();

//        if (captcha == null || !captcha.equalsIgnoreCase(sessionCaptcha)) {
//
//            throw new AuthenticationException();
//
//        }
        ShiroKit.getSubject().login(token);

        if (ShiroKit.getSubject().hasRole(LoginStatusEnums.ADMIN.getMessage())) {

            modelAndView.addObject("user", shiroUserDTO);

            session.setAttribute("user", shiroUserDTO);
            return "redirect:" + "/index";
        } else {

            modelAndView.addObject("user", shiroUserDTO);

            session.setAttribute("user", shiroUserDTO);

            return "redirect:" + "/seller/order/shoplist";
        }
    }
    @GetMapping("/logout")
    public String logout(){

        ShiroKit.getSubject().logout();

        return "redirect:" +"/login";
    }
    //验证码
    @GetMapping("/Captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 设置相应类型，告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        // 不缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        try {

            HttpSession session = request.getSession();

            CaptchaUtil tool = new CaptchaUtil();
            StringBuffer code = new StringBuffer();
            BufferedImage image = tool.genRandomCodeImage(code);
            session.removeAttribute(KEY_CAPTCHA);
            session.setAttribute(KEY_CAPTCHA, code.toString());

            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
