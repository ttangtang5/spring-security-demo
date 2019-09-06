package com.tang.security.core.validate;

import com.tang.security.core.exception.ValidateCodeException;
import com.tang.security.core.properties.SecurityProperties;
import com.tang.security.core.validate.image.ImageValidateCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description 验证码过滤器，校验验证码是否正确
 * 将它配置在Security配置中
 * OncePerRequestFilter:spring 工具类 保证每次只会调用一次
 * InitializingBean:spring两种初始化bean的方式，实现InitializingBean接口，实现afterPropertiesSet方法，或者在配置文件中通过init-method指定，两种方式可以同时使用。
 * @Author tang
 * @Date 2019-08-24 21:40
 * @Version 1.0
 **/
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(ValidateCodeFilter.class);

    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * mvc 的路径匹配工具类  匹配处理例如*的路径
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private Set<String> urlsSet = new HashSet<>();

    private SecurityProperties securityProperties;

    /**
     * 作用：在类其他属性方法初始化注入完之后执行此方法 ,注意直接new对象似乎不会触发
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getValidateCode().getImage().getValidateUrls(), ",");

        urlsSet = Arrays.stream(urls).collect(Collectors.toSet());
        // 将默认的校验路径加入
        urlsSet.add("/loginHandler");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean match = false;

        match = urlsSet.stream().anyMatch( url -> {
            return antPathMatcher.match(url, httpServletRequest.getRequestURI());
        });

        if (match) {
            try {
                logger.info("路径：{} 需要校验验证码！", httpServletRequest.getRequestURI());
                validate(httpServletRequest);
                logger.info("验证码校验成功！");
            } catch (ValidateCodeException e) {
                // 通过登录失败处理器处理
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 校验验证码
     * @param request
     */
    private void validate(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ImageValidateCode validateCode = (ImageValidateCode) session.getAttribute("validate_code_key");

        String requestCode = request.getParameter("validateCode");

        if (StringUtils.isBlank(requestCode)) {
            throw new ValidateCodeException("验证码为空！");
        }

        if (validateCode == null) {
            throw new ValidateCodeException("验证码不存在！");
        }

        if (validateCode.isExpried()) {
            throw new ValidateCodeException("验证码已过期！");
        }

        if (!StringUtils.equalsIgnoreCase(validateCode.getCode(),  requestCode)) {
            throw new ValidateCodeException("验证码错误！");
        }
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public Set<String> getUrlsSet() {
        return urlsSet;
    }

    public void setUrlsSet(Set<String> urlsSet) {
        this.urlsSet = urlsSet;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
