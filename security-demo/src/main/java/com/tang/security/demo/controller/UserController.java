package com.tang.security.demo.controller;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Description
 * @Author tang
 * @Date 2019-08-23 16:49
 * @Version 1.0
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping
    public String test() {
        return "test user";
    }

    /**
     * mvc 会自动从容器中将验证信息注入到authentication中
     * @param authentication
     * @return
     */
    @GetMapping("authMsg")
    public Object test1(Authentication authentication) {
        return authentication;
    }

    /**
     * 通过SecurityContextHolder获取验证信息
     * @return
     */
    @GetMapping("authMsg2")
    public Object test2() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 通过@AuthenticationPrincipal注解获取验证信息  暂未找到注入失败的原因
     * @param userDetails
     * @return
     */
    @GetMapping("authMsg3")
    public Object test3(@AuthenticationPrincipal User userDetails) {
        return userDetails;
    }

    /**
     * 通过principal获取验证信息
     * @return
     */
    @GetMapping("authMsg4")
    public Object test4(Principal principal) {
        return principal;
    }

}
