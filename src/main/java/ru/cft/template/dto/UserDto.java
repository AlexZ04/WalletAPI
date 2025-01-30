package ru.cft.template.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
public class UserDto {
    @Pattern(regexp = "[ЁА-Я][ЁА-Яёа-я]{0,49}")
    String lastName;
    @Pattern(regexp = "[ЁА-Я][ЁА-Яёа-я]{0,49}")
    String firstName;
    @Pattern(regexp = "[ЁА-Я][ЁА-Яёа-я]{0,49}")
    String middleName;
    @Pattern(regexp = "7\\d{10}")
    String phone;
    @Email
    String email;
    LocalDateTime birthday;
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!?])[A-Za-z\\d!?]{8,64}")
    String password;
}
