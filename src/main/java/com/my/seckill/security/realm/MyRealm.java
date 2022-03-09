package com.my.seckill.security.realm;

import com.my.seckill.dao.pojo.Seckilluser;
import com.my.seckill.service.impl.SeckilluserServiceImpl;
import com.my.seckill.util.MyApplicationContextUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){

        //获取用户
        String username =(String)authenticationToken.getPrincipal();

        //获取自定义ApplicationContext工具类,提供工具类活service方法
        MyApplicationContextUtil myApplicationContextUtil = new MyApplicationContextUtil();
        SeckilluserServiceImpl serviceImpl = (SeckilluserServiceImpl) myApplicationContextUtil.getBean("seckilluserServiceImpl");

        //判断用户是否存在，存在再进行认证
        Seckilluser userdecide = serviceImpl.userdecide(username);


        if(!ObjectUtils.isEmpty(userdecide)){

            //信息交由Shiro进行处理
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userdecide.getUsername(), userdecide.getPassword(), ByteSource.Util.bytes(userdecide.getPasswordslat()),this.getName());

            return simpleAuthenticationInfo;
        }
        return null;
    }
}
