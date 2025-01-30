package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    Long id;
    String lastName;
    String firstName;
    String middleName;
    @Column(unique = true)
    String phone;
    @Column(unique = true)
    String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String birthday;
    String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String createTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    String updateTime;
}
