package com.evilsay.ship.Constant;

/** Shiro内置过滤器常用名称
 * @Author: EvilSay
 * @Date: 2019/2/13 15:35
 */
public interface ShiroConstant {
    //    表示可以任何人可以访问
    String anon = "anon";
    //    需要认证才可以访问
    String authc = "authc";
    //    没有参数表示httpBasic认证
    String authcBasic = "authcBasic";
    //   指定资源需要哪些权限才可以访问
    String perms = "perms";
    //    退出
    String logout = "logout";
    //    用户必需已通过认证
    String role = "role";
    //    表示用户不一定需要通过认证，只要曾被记住过登录状态就可以正常发起请求
    String user = "user";
}
