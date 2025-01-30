package ru.cft.template.dto;

import lombok.Data;
import ru.cft.template.model.User;

import java.time.LocalDateTime;

@Data
public class UserDto {
    String lastName;
    String firstName;
    String middleName;
    String phone;
    String email;
    LocalDateTime birthday;
    String password;
    LocalDateTime createTime;
    LocalDateTime updateTime;

    public UserDto(User user) {
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.birthday = user.getBirthday();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
    }
}
