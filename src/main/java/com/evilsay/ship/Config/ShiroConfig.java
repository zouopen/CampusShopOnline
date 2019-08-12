package com.evilsay.ship.Config;

import com.evilsay.ship.Constant.CookieConstant;
import com.evilsay.ship.Constant.ShiroConstant;
import com.evilsay.ship.Security.ShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: EvilSay
 * @Date: 2019/2/12 20:46
 */
@Configuration
public class ShiroConfig {

    private static final long MILLIS_PER_SECOND = 1000;
    private static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    private static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    @Bean(name ="cacheManagers" )

    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return cacheManager;
    }


    /**
     * <p>自定义Realm</p>
     *
     * @return
     */
    @Bean
    public ShiroRealm getShiroRealm() {
        return new ShiroRealm();
    }

    /**
     * <p>自定义cookie</p>
     *
     * @return
     */
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(CookieConstant.TOKEN);
        simpleCookie.setMaxAge(864000);
        return simpleCookie;
    }

    /**
     * <p>session管理器</p>
     *
     * @return
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager());
//        全局会话超时时间
        sessionManager.setGlobalSessionTimeout(3 * MILLIS_PER_HOUR);
//        是否定时检查过期session
        sessionManager.setSessionValidationSchedulerEnabled(true);
//        是否开启删除无效的session对象
        sessionManager.setDeleteInvalidSessions(true);
//        设置session失效的扫描时间, 清理用户直接关闭浏览器造成的孤立会话 默认为 1个小时
//        设置该属性 就不需要设置 ExecutorServiceSessionValidationScheduler 底层也是默认自动调用ExecutorServiceSessionValidationScheduler
        sessionManager.setSessionValidationInterval(MILLIS_PER_HOUR);
//        设置会话ID
        sessionManager.setSessionIdCookie(simpleCookie());
//        取消URL后面的后缀
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        return sessionManager;
    }
    /**
     * <p>cookie管理器</p>
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //rememberme cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //SecretKey deskey = keygen.generateKey();
        //System.out.println(Base64.encodeToString(deskey.getEncoded()));
        byte[] cipherKey = Base64.decode("4AvVhmFLUs0KTA3Kprsdag==");
        cookieRememberMeManager.setCipherKey(cipherKey);
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        //记住我cookie生效时间,默认30天 ,单位秒：60 * 60 * 24 * 30
        simpleCookie.setMaxAge(259200);

        return simpleCookie;
    }
    /**
     * <p>安全管理器</p>
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();

        manager.setRealm(getShiroRealm());

        manager.setCacheManager(cacheManager());

        manager.setSessionManager(defaultWebSessionManager());
        manager.setRememberMeManager(rememberMeManager());
        return manager;
    }
    @Bean
        public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setUnauthorizedUrl("/login");


        Map<String, Filter> filters = new HashMap<>();
        //匿名过滤器
        filters.put(ShiroConstant.anon, new AnonymousFilter());
        //表单过滤器
        filters.put(ShiroConstant.authc, new FormAuthenticationFilter());
        //注销过滤器
        filters.put(ShiroConstant.logout, new LogoutFilter());
        //角色权限过滤器
        filters.put(ShiroConstant.role, new RolesAuthorizationFilter());
        //用户过滤器
        filters.put(ShiroConstant.user, new UserFilter());
        //设置过滤器
        shiroFilter.setFilters(filters);
        /**
         * 登录不拦截
         */
        Map<String, String> chains = new LinkedHashMap<>();
        chains.put("/login", ShiroConstant.anon);
        chains.put("/postLogin", ShiroConstant.anon);
        chains.put("/MobileLogin",ShiroConstant.anon);
        /**
         * 静态资源及授权不拦截
         * */
        chains.put("/adminlte/**", ShiroConstant.anon);
        chains.put("/css/**",ShiroConstant.anon);
        chains.put("/buyer/**",ShiroConstant.anon);
        chains.put("/mobile/**",ShiroConstant.anon);
        chains.put("/wechat/**",ShiroConstant.anon);
        /**
         * API可视化接口不拦截
         */
        chains.put("/swagger-ui.html**", ShiroConstant.anon);
        chains.put("/v2/api-docs", ShiroConstant.anon);
        chains.put("/swagger-resources/**",ShiroConstant.anon);
        chains.put("/webjars/**", ShiroConstant.anon);
        chains.put("/Captcha.jpg",ShiroConstant.anon);

        chains.put("/**",ShiroConstant.authc);
        shiroFilter.setFilterChainDefinitionMap(chains);
        return shiroFilter;
    }
    /**
     * <p>保证实现了Shiro内部lifecycle函数的bean执行</p>
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
