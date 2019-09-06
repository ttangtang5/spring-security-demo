package com.tang.security.core.properties;

/**
 * @Description 登录成功或失败之后 处理返回类型枚举类型
 * JSON：处理后信息已json字符串返回
 * HTML：处理后信息返回一个页面
 * @Author tang
 * @Date 2019-08-23 21:27
 * @Version 1.0
 **/
public enum LoginAfterHandlerType {
    JSON("json"), HTML("html");

    private String type;

    LoginAfterHandlerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
