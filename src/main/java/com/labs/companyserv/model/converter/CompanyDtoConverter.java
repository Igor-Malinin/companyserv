package com.labs.companyserv.model.converter;

import com.labs.microver4.entity.PgUser;
import com.labs.microver4.model.UserDto;

public class UserDtoConverter {

    public static PgUser toEntity(UserDto dto) {
        PgUser pgUser = new PgUser();
        pgUser.setId(dto.getId());
        pgUser.setName(dto.getName());
        pgUser.setEmail(dto.getEmail());
        pgUser.setLogin(dto.getLogin());
        pgUser.setPassword(dto.getPassword());
        pgUser.setEnabled(dto.getEnabled());
        pgUser.setCompanyId(dto.getCompanyId());

        return pgUser;
    }

    public static UserDto convertEntityToDto(PgUser pgUser) {
        UserDto userDto = new UserDto();

        userDto.setId(pgUser.getId());
        userDto.setName(pgUser.getName());
        userDto.setEmail(pgUser.getEmail());
        userDto.setLogin(pgUser.getLogin());
        userDto.setPassword(pgUser.getPassword());
        userDto.setEnabled(pgUser.getEnabled());
        userDto.setCompanyId(pgUser.getCompanyId());

        return userDto;
    }
}
