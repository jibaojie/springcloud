package com.example.springcloud.common.oauth2server.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * @author 70934
 */
public class ImageCodeException extends AuthenticationException {

    public ImageCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ImageCodeException(String msg) {
        super(msg);
    }
}
