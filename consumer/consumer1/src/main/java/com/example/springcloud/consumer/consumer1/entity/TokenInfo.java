package com.example.springcloud.consumer.consumer1.entity;

import lombok.Data;

/**
 * @author jbj
 * @create 2019-05-08 16:56
 */
@Data
public class TokenInfo {

    private String access_token;

    private String scope;

    private String token_type;

}
