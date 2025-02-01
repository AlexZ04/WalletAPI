package ru.cft.template.model;

import jakarta.persistence.*;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import ru.cft.template.utility.SecurityUtility;
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
    private LocalDateTime createTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updateTime;
    @OneToOne(cascade = CascadeType.ALL)
    private Wallet wallet;

    public User() {}

    public User(String lastName, String firstName, String middleName, String phone, String email,
                LocalDateTime birthday, String password, LocalDateTime createTime, LocalDateTime updateTime) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
