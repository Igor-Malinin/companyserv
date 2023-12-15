package com.labs.companyserv.model.converter;

import com.labs.companyserv.entity.PgCompany;
import com.labs.companyserv.model.CompanyDto;

public class CompanyDtoConverter {

    public static PgCompany toEntity(CompanyDto dto) {
        PgCompany pgCompany = new PgCompany();
        pgCompany.setId(dto.getId());
        pgCompany.setCompanyName(dto.getCompanyName());
        pgCompany.setDirectorId(dto.getDirectorId());
        pgCompany.setDescription(dto.getDescription());
        pgCompany.setDeleted(dto.isDeleted());

        return pgCompany;
    }

    public static CompanyDto toDto(PgCompany pgCompany) {
        CompanyDto companyDto = new CompanyDto();

        companyDto.setId(pgCompany.getId());
        companyDto.setCompanyName(pgCompany.getCompanyName());
        companyDto.setDirectorId(pgCompany.getDirectorId());
        companyDto.setDescription(pgCompany.getDescription());
        companyDto.setDeleted(pgCompany.isDeleted());

        return companyDto;
    }
}
