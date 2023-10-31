package com.labs.companyserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CompanyservApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyservApplication.class, args);
    }

}
