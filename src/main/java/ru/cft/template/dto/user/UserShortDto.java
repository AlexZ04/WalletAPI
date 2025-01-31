package ru.cft.template.dto.user;

import lombok.Builder;
import lombok.Data;
import ru.cft.template.model.User;

import java.time.LocalDateTime;

@Data
@Builder
public class UserShortDto {
    private String firstName;
    private String lastName;
}
