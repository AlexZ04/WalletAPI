package ru.cft.template.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class UserDto {
    String lastName;
    String firstName;
    String middleName;
    String phone;
    String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String birthday;
    String password;
}
