package ru.cft.template.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserModel {
    int id;
    String lastName;
    String firstName;
    String middleName;
    String phone;
    String email;
    LocalDate birthday;
    String password;
    LocalDate createTime;
}
