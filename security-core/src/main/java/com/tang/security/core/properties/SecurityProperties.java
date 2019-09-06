package com.tang.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description 系统属性配置
 * 如果一个配置类只配置@ConfigurationProperties注解，而没有使用@Component，那么在IOC容器中是获取不到properties 配置文件转化的bean。
 * 说白了 @EnableConfigurationProperties 相当于把使用  @ConfigurationProperties 的类进行了一次注入。
 * @Author tang
 * @Date 2019-08-23 18:28
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "tang.security")
public class SecurityProperties {

    /**
     * web模式属性配置
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码属性配置
     */
    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }
}
