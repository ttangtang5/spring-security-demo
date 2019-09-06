package com.tang.security.core.properties;

/**
 * @Description 图形验证码配置属性
 * @Author tang
 * @Date 2019-08-24 20:27
 * @Version 1.0
 **/
public class ImageValidateCodeProperties {

    /**
     * 图形长度
     */
    private Integer width = 70;

    /**
     * 图形高度
     */
    private Integer height = 20;

    /**
     * 验证码长度
     */
    private Integer codeLength = 4;

    /**
     * 有效期 单位秒
     */
    private Integer expireIn = 180;

    /**
     * 需要校验图形验证码的请求路径，多个","分割
     */
    private String validateUrls = "/loginHandler";

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Integer expireIn) {
        this.expireIn = expireIn;
    }

    public String getValidateUrls() {
        return validateUrls;
    }

    public void setValidateUrls(String validateUrls) {
        this.validateUrls = validateUrls;
    }
}
