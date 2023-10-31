package com.labs.companyserv.model;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyDto {
    private String id;
    private String companyName;
    private String directorId;
    private String directorName;
}
