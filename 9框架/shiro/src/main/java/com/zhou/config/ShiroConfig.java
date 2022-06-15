package com.zhou.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.config.ShiroAnnotationProcessorConfiguration;
import org.apache.shiro.spring.config.ShiroConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.HashMap;


/**
 * @author zhouhelong
 * @creat 2022-06-10 18:35
 * @description:
 */
@Configuration
@Import({ShiroConfiguration.class,
        ShiroAnnotationProcessorConfiguration.class})
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultSecurityManager") DefaultSecurityManager defaultSecurityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultSecurityManager);
        factoryBean.setLoginUrl("/tologin");
        factoryBean.setSuccessUrl("/");
        HashMap<String, String> fifterMap = new HashMap<>();
        fifterMap.put("/", "authc");
        factoryBean.setFilterChainDefinitionMap(fifterMap);
        return factoryBean;
    }

    @Bean(name = "defaultSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager(userRealm);
        return manager;
    }

    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }


}
