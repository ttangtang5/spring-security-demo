package com.tang.security.browser.handler;

import com.alibaba.fastjson.JSON;
import com.tang.security.core.properties.LoginAfterHandlerType;
import com.tang.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description AuthenticationSuccessHandler为原始处理接口 SavedRequestAwareAuthenticationSuccessHandler为Security默认的实现方式即跳转原有请求
 *  为了动态控制 处理器的返回类型 才继承默认实现
 * @Author tang
 * @Date 2019-08-23 21:33
 * @Version 1.0
 **/
@Component
public class BrowserLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(BrowserLoginSuccessHandler.class);

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("用户：{} 登录成功！", authentication.getName());
        logger.info("登录成功！后置处理方式：{}", securityProperties.getBrowser().getLoginAfterHandlerType());

        if (StringUtils.equalsIgnoreCase(securityProperties.getBrowser().getLoginAfterHandlerType(), LoginAfterHandlerType.JSON.getType())) {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(authentication));
        } else {
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }
    }
}
