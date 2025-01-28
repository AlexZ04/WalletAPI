package ru.cft.template.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    String lastName;
    String firstName;
    String middleName;
    String phone;
    String email;
    LocalDate birthday;
    String password;
}
