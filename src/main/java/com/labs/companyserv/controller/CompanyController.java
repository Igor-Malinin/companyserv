package com.labs.companyserv.controller;

import com.labs.companyserv.configuration.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class PropertyController {

    private final Config config;

    @GetMapping
    public String getCompanyProperty() {
        return config.getCompanyProperty();
    }

}
