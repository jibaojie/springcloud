package com.example.springcloud.common.oauth2server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author jbj
 * @create 2019-05-09 10:23
 * @desc 授权
 * EnableAuthorizationServer 声明OAuth2 的认证中心
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ApprovalStore approvalStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 允许表单验证，浏览器直接发送post请求即可获取tocken
     * 在令牌端点上定义安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.realm("OAuth2-Sample")
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 用于校验注册的第三方客户端的信息
     *
     * 定义客户端细节服务,客户详细信息可以被初始化。用来定义一个基于内存的或者JDBC的客户端信息服务
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //添加客户端信息
        //使用内存存储OAuth客户端信息
        clients.inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret("secret")
                // 该client允许的授权类型，不同的类型，则获得token的方式不一样。
                .authorizedGrantTypes("authorization_code","implicit","refresh_token")
                .resourceIds("resourceId")
                //回调uri，在authorization_code与implicit授权方式时，用以接收服务器的返回信息
                .redirectUris("http://localhost:8090/")
                // 允许的授权范围
                .scopes("app","test");
//        super.configure(clients);
//            clients.withClientDetails(new MyClientDetailsService());

    }

    /**
     * 主要的作用用于控制token的端点等信息
     * 定义授权和令牌端点和令牌服务
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //reuseRefreshTokens设置为false时，每次通过refresh_token获得access_token时，也会刷新refresh_token；也就是说，会返回全新的access_token与refresh_token。
        //默认值是true，只返回新的access_token，refresh_token不变。
        endpoints.tokenStore(tokenStore).approvalStore(approvalStore).reuseRefreshTokens(false)
                .authenticationManager(authenticationManager);
    }


    @Bean
    public TokenStore tokenStore() {
        //token保存在内存中（也可以保存在数据库、Redis中）。
        //如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
        //注意：如果不保存access_token，则没法通过access_token取得用户信息
        return new InMemoryTokenStore();
    }

    @Bean
    public ApprovalStore approvalStore() throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

}
