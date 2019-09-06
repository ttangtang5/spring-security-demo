package com.tang.security.browser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Description security配置
 * @Author tang
 * @Date 2019-08-23 16:51
 * @Version 1.0
 **/
@Component
public class BrowserUserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(BrowserUserService.class);

    /**
     * 在实际应用中此处是不需要的，在注册出需要引入 对密码加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username 为登录输入的用户名。此处可通过它去数据库查找对应用户 返回给security
        // 此处用的是Security中的User 自写的pojo需实现UserDetails即可
        logger.info("登录的用户名为：{}", username);
        String tang = passwordEncoder.encode("tang");
        logger.info("数据库信息：用户名：{}，密码：{}", username, tang);
        User user = new User(username, tang, AuthorityUtils.commaSeparatedStringToAuthorityList("user"));

        //throw new UsernameNotFoundException("没找到对应用户");
        return user;
    }
}
