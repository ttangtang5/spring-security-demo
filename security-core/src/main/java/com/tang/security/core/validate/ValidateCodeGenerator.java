package com.tang.security.core.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Description 验证码生成接口
 * @Author tang
 * @Date 2019-08-24 20:17
 * @Version 1.0
 **/
public interface ValidateCodeGenerator {

    ValidateCode generator(ServletWebRequest request);
}
