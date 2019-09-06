package com.tang.security.core.validate;

import com.tang.security.core.validate.image.ImageValidateCode;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description  验证码端口
 * @Author tang
 * @Date 2019-08-24 20:41
 * @Version 1.0
 **/
@RestController
public class ValidateCodeController {

    private Logger logger = LoggerFactory.getLogger(ValidateCodeController.class);

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    private final String VALIDATE_CODE_KEY = "validate_code_key";

    @GetMapping(value = "validate/{type}")
    public void createValidateCode(HttpServletRequest request, HttpServletResponse response,@PathVariable String type) throws IOException {
        logger.info("请求生成验证码的类型：{}", type);

        if (StringUtils.equalsIgnoreCase(type, "image")) {
            ImageValidateCode imageValidateCode = (ImageValidateCode) validateCodeGenerator.generator(new ServletWebRequest(request));
            // TODO 存在系统第一次启动 第一次访问sessionId问题 导致校验出现验证码不存在错误
            HttpSession session = request.getSession(true);
            session.setAttribute(VALIDATE_CODE_KEY, imageValidateCode);

            logger.info("图形验证码：{}，有效期：{}", imageValidateCode.getCode(), imageValidateCode.getExpireTime());
            ImageIO.write(imageValidateCode.getImage(), "JPEG", response.getOutputStream());

        }

    }
}
