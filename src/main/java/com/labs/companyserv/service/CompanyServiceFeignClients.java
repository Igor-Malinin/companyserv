package com.labs.companyserv.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service",
        path = "/user",
        url = "http://localhost:8040"
)
public interface CompanyServiceFeignClients {
    @GetMapping("/exists-by-id/{id}")
    Boolean existsById (@PathVariable String id);

    @GetMapping("/get-dir-name/{id}")
    String getDirName (@PathVariable String id);
}
