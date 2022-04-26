package com.springbatch.springbatchtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "com.springbatch.springbatchtest")
@AutoConfigurationPackage
//@SpringCloudApplication
class SpringbatchtestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbatchtestApplication.class, args);
    }

}
