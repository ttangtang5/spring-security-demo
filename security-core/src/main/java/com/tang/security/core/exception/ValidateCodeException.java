package com.tang.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description 验证码验证异常
 * @Author tang
 * @Date 2019-08-24 21:46
 * @Version 1.0
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
