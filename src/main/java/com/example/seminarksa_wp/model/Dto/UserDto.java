package com.example.seminarksa_wp.model.Dto;

import com.example.seminarksa_wp.model.enumerations.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {
    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private String password;

    private String reTypePassword;

    private String country;

    private Role role;

}
