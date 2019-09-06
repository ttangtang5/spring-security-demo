package com.tang.security.core.config;

import com.tang.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 开启属性读取配置
 * @Author tang
 * @Date 2019-08-23 18:31
 * @Version 1.0
 **/
@Configuration
@EnableConfigurationProperties(value = {SecurityProperties.class})
public class PropertiesConfig {
}
