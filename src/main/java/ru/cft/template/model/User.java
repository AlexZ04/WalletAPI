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
    Long id;
    String lastName;
    String firstName;
    String middleName;
    @Column(unique = true)
    String phone;
    @Column(unique = true)
    String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime birthday;
    String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime createTime = LocalDateTime.now();
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime updateTime = null;

    public User() {}

    public User(UserCreateDto user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.middleName = user.getMiddleName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.password = Security.hashPassword(user.getPassword());
    }
}
