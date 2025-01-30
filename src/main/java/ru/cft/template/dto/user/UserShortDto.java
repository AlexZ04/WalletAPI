package ru.cft.template.dto.user;

import lombok.Data;
import ru.cft.template.model.User;

import java.time.LocalDateTime;

@Data
public class UserShortDto {
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDateTime birthday;

    public UserShortDto(User user) {
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.birthday = user.getBirthday();
    }
}
