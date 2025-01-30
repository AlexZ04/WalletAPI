package ru.cft.template.dto.user;

import lombok.Data;

import jakarta.validation.constraints.*;
import ru.cft.template.model.User;

import java.time.LocalDateTime;

@Data
public class UserCreateDto {
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

    public UserCreateDto() {}

}
