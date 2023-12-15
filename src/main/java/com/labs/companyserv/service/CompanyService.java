package com.labs.companyserv.service;

import com.labs.companyserv.entity.PgCompany;
import com.labs.companyserv.model.CompanyDto;
import com.labs.companyserv.model.converter.CompanyDtoConverter;
import com.labs.companyserv.repository.PgCompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    @Autowired
    private PgCompanyRepository pgCompanyRepository;

    @Autowired
    private CompanyServiceFeignClients companyServiceFeignClients;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.producer.topic.company-deleted}")
    private String companyDeletedTopic;

    @Transactional
    public String createCompany(CompanyDto companyDto) {
        Boolean exist = companyServiceFeignClients.existsById(companyDto.getDirectorId());
        if(!exist) {
            throw new EntityNotFoundException("Директор с id = %s не существует".formatted(companyDto.getDirectorId()));
        }

        return pgCompanyRepository.save(CompanyDtoConverter.toEntity(companyDto)).getId();
    }

    public Boolean existsById(String id) {
        Optional<PgCompany> pgCompany = pgCompanyRepository.findById(id);
        EntityNotFoundException exception = new EntityNotFoundException("компания не найдена");;
        if(pgCompany.isPresent()) {
            if(!pgCompany.get().isDeleted())
                return pgCompanyRepository.existsById(id);
            else
                throw exception;
        }
        else
            throw exception;
    }

    public CompanyDto getById(String id) {
        EntityNotFoundException exception = new EntityNotFoundException(
                "Компания с id: " + id + " - не существует");
        CompanyDto companyDto = CompanyDtoConverter
                .toDto(pgCompanyRepository.findById(id)
                .orElseThrow(() -> exception));
        if(!companyDto.isDeleted())
            return companyDto;
        else
            throw exception;
    }

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

    public ResponseEntity<String> deleteCompany(String id)  {
        PgCompany pgCompany = pgCompanyRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Компания не найдена"));
        pgCompany.setDeleted(true);
        pgCompanyRepository.save(pgCompany);
        kafkaTemplate.send(companyDeletedTopic, id);

        return ResponseEntity.ok("Компания удалена");
    }

    @KafkaListener(topics = "${spring.kafka.consumer.topic.company-deleted-user}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void handleCompanyDeleted(String id) {
        Optional<PgCompany> pgCompany = pgCompanyRepository.findById(id);
        if(pgCompany.isPresent())
            pgCompanyRepository.delete(pgCompany.get());
        else
            throw new EntityNotFoundException("Компания не найдена");
    }
}
