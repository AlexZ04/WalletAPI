package ru.cft.template.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class UserUpdateDto {
    @Pattern(regexp = "[ЁА-Я][ЁА-Яёа-я]{0,49}")
    public String lastName;
    @Pattern(regexp = "[ЁА-Я][ЁА-Яёа-я]{0,49}")
    public String firstName;
    @Pattern(regexp = "[ЁА-Я][ЁА-Яёа-я]{0,49}")
    public String middleName;
    LocalDateTime birthday;
}
