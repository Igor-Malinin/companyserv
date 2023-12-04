package com.labs.companyserv.controller;

import com.labs.companyserv.configuration.Config;
import com.labs.companyserv.model.CompanyDto;
import com.labs.companyserv.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/create")
    public String createCompany(@RequestBody CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

    @GetMapping("/exists-by-id/{companyId}")
    Boolean existsById (@PathVariable String companyId) {
        return companyService.existsById(companyId);
    }

    @GetMapping("/get-by-id/{companyId}")
    CompanyDto getById (@PathVariable String companyId) {
        return companyService.getById(companyId);
    }

    @GetMapping("/get-all")
    List<CompanyDto> getAllCompanies () {
        return companyService.getAllCompanies();
    }

}



//    private final Config config;
//
//    @GetMapping("/property")
//    public String getCompanyProperty() {
//        return config.getCompanyProperty();
//    }
//
//    @GetMapping("/desc")
//    public String getDescription() {
//        return config.getDescription();
//    }
