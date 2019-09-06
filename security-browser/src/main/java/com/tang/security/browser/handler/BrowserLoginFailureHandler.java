package com.tang.security.browser.handler;

import com.alibaba.fastjson.JSON;
import com.tang.security.core.properties.LoginAfterHandlerType;
import com.tang.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description AuthenticationFailureHandler为原始处理接口 SimpleUrlAuthenticationFailureHandler为Security默认的实现方式即跳转原有请求
 * 为了动态控制 处理器的返回类型 才继承默认实现
 * @Author tang
 * @Date 2019-08-23 21:46
 * @Version 1.0
 **/
@Component
public class BrowserLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(BrowserLoginFailureHandler.class);

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("登录失败！失败原因：{}", e.getMessage());
        logger.info("登录失败！后置处理方式：{}", securityProperties.getBrowser().getLoginAfterHandlerType());

        if (StringUtils.equalsIgnoreCase(securityProperties.getBrowser().getLoginAfterHandlerType(), LoginAfterHandlerType.JSON.getType())) {
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(e.getMessage()));
        } else {
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }


    }
}
