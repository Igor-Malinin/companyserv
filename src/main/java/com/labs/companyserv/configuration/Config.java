package com.labs.companyserv.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class Config {
    @Value("${my.property.company}")
    private String companyProperty;
}
