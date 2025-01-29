package ru.cft.template.model;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

@Data
public class User {
    int id;
    String lastName;
    String firstName;
    String middleName;
    String phone;
    String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String birthday;
    String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String createTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String updateTime;
}
