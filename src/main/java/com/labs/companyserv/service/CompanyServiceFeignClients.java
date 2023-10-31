package com.labs.companyserv.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        path = "/users",
        url = "http://localhost:8082"
)
public interface CompanyServiceFeignClients {
    @GetMapping("/exists-by-id/{id}")
    Boolean existsById (@PathVariable String id);
}
