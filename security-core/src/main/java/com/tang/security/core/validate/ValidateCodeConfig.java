package com.tang.security.core.validate;

import com.tang.security.core.properties.SecurityProperties;
import com.tang.security.core.validate.image.DefaultImageValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author tang
 * @Date 2019-08-24 21:21
 * @Version 1.0
 **/
@Configuration
public class ValidateCodeConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(value = {ValidateCodeGenerator.class})
    public ValidateCodeGenerator defaultImageValidateCodeGenerator() {
        DefaultImageValidateCodeGenerator validateCodeGenerator = new DefaultImageValidateCodeGenerator();
        validateCodeGenerator.setSecurityProperties(securityProperties);

        return validateCodeGenerator;
    }
}
