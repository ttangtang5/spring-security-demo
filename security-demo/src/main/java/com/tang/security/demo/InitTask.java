package com.tang.security.demo;

import com.tang.security.browser.service.BrowserUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @Description
 * @Author tang
 * @Date 2019-08-23 16:51
 * @Version 1.0
 **/
@Component
public class InitTask implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public void run(String... args) throws Exception {
        BrowserUserService bean = applicationContext.getBean(BrowserUserService.class);
        Assert.notNull(bean, "没有获取到");
    }
}
