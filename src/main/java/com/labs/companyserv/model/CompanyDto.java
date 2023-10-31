package com.labs.microver4.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String login;
    private String password;
    private Boolean enabled;
    private Long companyId;
}
