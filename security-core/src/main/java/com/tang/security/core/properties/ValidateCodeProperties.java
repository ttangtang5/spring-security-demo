package com.tang.security.core.properties;

import java.time.LocalDateTime;

/**
 * @Description 验证码配置属性
 * @Author tang
 * @Date 2019-08-24 20:27
 * @Version 1.0
 **/
public class ValidateCodeProperties {

    /**
     * 图形验证码
     */
    private ImageValidateCodeProperties image = new ImageValidateCodeProperties();

    public ImageValidateCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageValidateCodeProperties image) {
        this.image = image;
    }

}
