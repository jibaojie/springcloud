package com.example.springcloud.consumer.comsumer3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Comsumer3Application {

    public static void main(String[] args) {
        SpringApplication.run(Comsumer3Application.class, args);
    }

}
