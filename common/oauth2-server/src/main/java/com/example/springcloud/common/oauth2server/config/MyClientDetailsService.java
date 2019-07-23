package com.example.springcloud.common.oauth2server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * @author jbj
 * @create 2019-05-14 8:51
 */
@Configuration
public class MyClientDetailsService implements ClientDetailsService {

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        //根据clientId获取信息
        return null;
    }

}
