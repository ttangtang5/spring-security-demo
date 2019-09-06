package com.tang.security.demo;

import com.tang.security.core.validate.ValidateCode;
import com.tang.security.core.validate.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author tang
 * @Date 2019-08-24 22:36
 * @Version 1.0
 **/
//@Component
public class CustomValidateCodeGenerator implements ValidateCodeGenerator {

    public ValidateCode generator(ServletWebRequest request) {
        System.out.println("自定义验证码生成器");
        return null;
    }
}
