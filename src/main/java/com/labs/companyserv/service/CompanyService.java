package com.labs.microver4.service;

import com.labs.microver4.model.UserDto;
import com.labs.microver4.model.converter.UserDtoConverter;
import com.labs.microver4.repository.PgUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final PgUserRepository pgUserRepository;
    private final CompanyServiceFeignClients companyServiceFeignClients;

    @Transactional
    public String createUser(UserDto userDto) {
        Boolean exist = companyServiceFeignClients.existsById(userDto.getCompanyId());
        if(!exist) {
            throw new EntityNotFoundException("Компания с id = %s не существует".formatted(userDto.getCompanyId()));
        }

        return pgUserRepository.save(UserDtoConverter.toEntity(userDto)).getId();
    }

    @Transactional
    public UserDto getUserDtoById(String id) {
        Boolean exist = companyServiceFeignClients.existsById(userDto.getCompanyId());
        if(!exist) {
            throw new EntityNotFoundException("Компания с id = %s не существует".formatted(userDto.getCompanyId()));
        }

        return pgUserRepository.save(UserDtoConverter.toEntity(userDto)).getId();
    }
}
