package ru.gb.angular.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AngularJSApplication {
    public static void main(String[] args) {
        SpringApplication.run(AngularJSApplication.class, args);
    }
}
