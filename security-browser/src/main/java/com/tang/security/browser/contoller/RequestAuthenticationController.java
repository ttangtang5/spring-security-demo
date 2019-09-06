package com.tang.security.browser.contoller;

import com.tang.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 判断未验证的请求，根据其类型进行信息反馈。请求数据资源返回json，请求页面返回提示页面
 * @Author tang
 * @Date 2019-08-23 18:46
 * @Version 1.0
 **/
@RestController
@RequestMapping("request")
public class RequestAuthenticationController {

    private Logger logger = LoggerFactory.getLogger(RequestAuthenticationController.class);

    /**
     * 在跳转之前 security会将请求缓存在HttpSessionRequestCache中。
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 根据请求类型决定是否登录，判断无验证的请求，所请求的资源为页面还是数据。页面就跳转为登录页面，数据请求就返回信息提示
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("authentication")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Map authenticationResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 取出跳转之前的请求。
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        //TODO 此处判断不够严谨  部分错误请求被处理，特别是spring boot自带的处理端口
        if (savedRequest != null) {
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("跳转请求：{}", redirectUrl);
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }

        Map result = new HashMap();
        result.put("message", "无资源访问权限");

        return result;
    }
}
