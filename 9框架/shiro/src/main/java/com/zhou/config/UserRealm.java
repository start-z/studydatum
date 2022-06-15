package com.zhou.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author zhouhelong
 * @creat 2022-06-10 18:35
 * @description:  shiro 检测
 *
 */
@Slf4j
public class UserRealm extends AuthorizingRealm  {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("我进入了鉴权");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
      log.info(username);
        return   new SimpleAuthenticationInfo(token.getPrincipal(),token.getCredentials(),username);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("获取授权");
        return null;
    }
}
