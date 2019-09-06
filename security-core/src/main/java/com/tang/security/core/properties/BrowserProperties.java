package com.tang.security.core.properties;

/**
 * @Description web端 配置文件
 * @Author tang
 * @Date 2019-08-23 18:41
 * @Version 1.0
 **/
public class BrowserProperties {

    /**
     * 登录页面
     */
    private String loginPage = "/page/browser-login.html";

    /**
     * 登录之后信息返回类型
     */
    private String loginAfterHandlerType = LoginAfterHandlerType.JSON.getType();

    /**
     * rememberMe 的有效期 单位秒
     */
    private Integer rememberMeExpireIn = 600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginAfterHandlerType() {
        return loginAfterHandlerType;
    }

    public void setLoginAfterHandlerType(String loginAfterHandlerType) {
        this.loginAfterHandlerType = loginAfterHandlerType;
    }

    public Integer getRememberMeExpireIn() {
        return rememberMeExpireIn;
    }

    public void setRememberMeExpireIn(Integer rememberMeExpireIn) {
        this.rememberMeExpireIn = rememberMeExpireIn;
    }
}
