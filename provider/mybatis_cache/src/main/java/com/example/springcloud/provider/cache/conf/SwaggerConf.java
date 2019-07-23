package com.example.springcloud.provider.cache.conf;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jbj
 * @create 2019-07-07 17:43
 * @description swagger配置，注意有扫描不到接口或者swagger页面打不开的情况，可尝试清除浏览器缓存
 */
@Configuration
@EnableSwagger2
public class SwaggerConf {

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 RESTful APIs")
                .termsOfServiceUrl("localhost:8080/")
                .contact(new Contact("121", "http://localhost:8080/", "weepal.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.basePackage("com.example.springcloud.provider.cache.controller"))
                .paths(PathSelectors.any())
                .build();
    }


}
