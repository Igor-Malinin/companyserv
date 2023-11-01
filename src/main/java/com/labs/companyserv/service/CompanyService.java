package com.labs.companyserv.service;

import com.labs.companyserv.entity.PgCompany;
import com.labs.companyserv.model.CompanyDto;
import com.labs.companyserv.model.converter.CompanyDtoConverter;
import com.labs.companyserv.repository.PgCompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {
    private final PgCompanyRepository pgCompanyRepository;
    private final CompanyServiceFeignClients companyServiceFeignClients;

    @Transactional
    public String createCompany(CompanyDto companyDto) {
        Boolean exist = companyServiceFeignClients.existsById(companyDto.getDirectorId());
        if(!exist) {
            throw new EntityNotFoundException("Директор с id = %s не существует".formatted(companyDto.getDirectorId()));
        }

        return pgCompanyRepository.save(CompanyDtoConverter.toEntity(companyDto)).getId();
    }

    @Transactional
    public Boolean existsById(String id) {
        return pgCompanyRepository.existsById(id);
    }

    @Transactional
    public CompanyDto getById(String id) {
        return CompanyDtoConverter.toDto(pgCompanyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Компания с id: " + id + " - не существует")));
    }

    @Transactional
    public List<CompanyDto> getAllCompanies() {
        List<PgCompany> pgCompanies = pgCompanyRepository.findAll();

        List<CompanyDto> companyDtos = new ArrayList<>();
        for (PgCompany entity : pgCompanies) {
            CompanyDto companyDto = CompanyDtoConverter.toDto(entity);
            companyDto.setDirectorName(companyServiceFeignClients.getDirName(entity.getDirectorId()));
            companyDtos.add(companyDto);
        }
        return companyDtos;
    }
}
