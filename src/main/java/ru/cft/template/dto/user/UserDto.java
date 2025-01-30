package ru.cft.template.dto.user;

import lombok.Data;
import ru.cft.template.model.User;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;
    private LocalDateTime birthday;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

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
