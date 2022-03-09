package com.my.seckill.config;

import com.my.seckill.security.realm.MyRealm;
import com.my.seckill.security.shiro_redis.MyCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

@Configuration
public class MyShiroConfig {

    @Bean//设置shiro过滤器，防止资源盗链
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        //设置过滤器
        Map<String,String> FilterMap = new HashMap<>();
        FilterMap.put("/static/**","anon");
        FilterMap.put("/**","authc");
        FilterMap.put("/admin/**","anon");
        FilterMap.put("/druid/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(FilterMap);
        return shiroFilterFactoryBean;
    }

    @Bean//配置安全管理器
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //配置安全管理器的Realm
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }

    @Bean//配置Realm数据源(获取会话的数据进行授权认证)
    public Realm realm(){
        MyRealm realm = new MyRealm();
//        //设置缓存管理器
//        realm.setCacheManager(new MyCacheManager());
//        //开启全局缓存
//        realm.setCachingEnabled(true);
//        //开启认证缓存
//        realm.setAuthenticationCachingEnabled(true);
//        //开启授权缓存
//        realm.setAuthorizationCachingEnabled(true);
//        //设置缓存名称
//        realm.setAuthenticationCacheName("My_seckill_Authentication");
//        realm.setAuthorizationCacheName("My_seckill_Authorization");

        //修改凭证效验匹配器(正对MD5和Slat加密)
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");//设置加密的算法
        hashedCredentialsMatcher.setHashIterations(1024);//设置散列的次数
        //realm设置匹配器
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }




}
