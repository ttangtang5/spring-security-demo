package com.tang.security.browser.config;

import com.tang.security.core.properties.SecurityProperties;
import com.tang.security.core.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Description security 配置
 * @Author tang
 * @Date 2019-08-22 22:26
 * @Version 1.0
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)// 将验证码过滤器设置到 security身份验证前面
                .formLogin() // 表单验证
                //http.httpBasic()// http 基础验证
                .loginPage("/request/authentication") // 根据请求类型 决定是否登录
                .loginProcessingUrl("/loginHandler") // 表示usernamePasswordAuthenticationFilter作用该url
                .successHandler(authenticationSuccessHandler) // 登录成功处理
                .failureHandler(authenticationFailureHandler) // 登录失败处理
                .and()
                .rememberMe() // rememberMe配置
                .tokenRepository(this.persistentTokenRepository()) // 配置token查询实现
                .userDetailsService(userDetailsService) // 验证成功通过它获取信息
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeExpireIn()) // remeberMe有效时间 单位秒
                .and()
                .authorizeRequests()
                .antMatchers( "/**/favicon.ico", "/request/authentication", "/validate/*", securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable(); // 注意关闭跨站伪造请求
    }

    /**
     * 配置userDetailsService和passwordEncoder加密方式
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * 设置加密方式  可以返回自定义的加密的方式，自定义加密类实现passwordEncoder即可
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 实现rememberMe 数据库配置
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        // 尽可能直接运行jdbcTokenRepository的创建表的语句， 下面每次启动都运行，会导致创建表冲突报错
        jdbcTokenRepository.setCreateTableOnStartup(true);

        return jdbcTokenRepository;
    }
}
