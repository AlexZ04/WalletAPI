package ru.cft.template.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserUpdateDto {
    public String lastName;
    public String firstName;
    public String middleName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String birthday;
}
