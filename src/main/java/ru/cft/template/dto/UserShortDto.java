package ru.cft.template.dto;

import lombok.Data;
import ru.cft.template.model.User;

import java.time.LocalDateTime;

@Data
public class UserShortDto {
    String lastName;
    String firstName;
    String middleName;
    LocalDateTime birthday;

    public UserShortDto(User user) {
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.birthday = user.getBirthday();
    }
}
