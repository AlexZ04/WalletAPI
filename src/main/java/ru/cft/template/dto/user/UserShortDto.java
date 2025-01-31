package ru.cft.template.dto.user;

import lombok.Data;
import ru.cft.template.model.User;

import java.time.LocalDateTime;

@Data
public class UserShortDto {
    private String firstName;
    private String lastName;

    public UserShortDto(User user) {
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName().charAt(0) + ".";
    }
}
