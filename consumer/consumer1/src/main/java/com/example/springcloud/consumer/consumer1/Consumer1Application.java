package com.example.springcloud.consumer.consumer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Consumer1Application {


    public static void main(String[] args) {
        SpringApplication.run(Consumer1Application.class, args);
    }

    @Bean
    @LoadBalanced
    public RestOperations getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}
