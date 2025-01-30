package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import ru.cft.template.configuration.Security;
import ru.cft.template.dto.user.UserCreateDto;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime birthday;
    private String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createTime = LocalDateTime.now();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateTime = null;

    public User() {}

    public User(UserCreateDto user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        middleName = user.getMiddleName();
        phone = user.getPhone();
        email = user.getEmail();
        birthday = user.getBirthday();
        password = Security.hashPassword(user.getPassword());
    }
}
